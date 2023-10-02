package com.example.coinapp.presentation.Coin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.coinapp.R
import com.example.coinapp.databinding.FragmentCoin1Binding
import com.example.coinapp.databinding.FragmentCoinBinding
import com.example.coinapp.presentation.CoinList.CoinAdapter
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CoinFragment1 : Fragment() {

    private lateinit var binding: FragmentCoin1Binding
    private val coinVIewModel : CoinViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCoin1Binding.inflate(inflater, container, false)
        val view = binding.root

        requireActivity().intent?.let {
            val coinId = it.getStringExtra("id")?:""
            if(coinId.isNotBlank()) {
                coinVIewModel.getCoinById(coinId.toString())
                observeCoinDetail()
            }else   {
                Toast.makeText(requireContext(), "We don't have any id to call", Toast.LENGTH_LONG).show()
            }

        }

        return view
    }

    private fun observeCoinDetail() {
        CoroutineScope(Dispatchers.IO).launch {
            coinVIewModel._coinValue.collectLatest { value->
                if (value.isLoading) {
                    binding.coinProgressBar2.visibility = View.VISIBLE
                } else if(value.error.isNotBlank()) {
                    binding.coinProgressBar2.visibility = View.GONE
                    Toast.makeText(requireContext(),value.error,Toast.LENGTH_LONG).show()
                }else {
                    binding.coinProgressBar2.visibility = View.GONE
                    value.coinDetail?.let {coinDetail ->
                        Picasso.get().load(coinDetail.image).into(binding.imageCoinDetail)
                        binding.txtCoinName.text = coinDetail.name
                        binding.txtCoinPrice.text = coinDetail.price.toString()
                        binding.txtCoinPriceLow.text = coinDetail.low_price.toString()
                        binding.txtCoinHighPrice.text = coinDetail.high_price.toString()
                        binding.txtCoinMarketCap.text = coinDetail.price_percentage_change.toString()
                        binding.txtCoinPricePercentChange.text = coinDetail.price_percentage_change.toString()
                    }

                }
            }

        }
    }
}