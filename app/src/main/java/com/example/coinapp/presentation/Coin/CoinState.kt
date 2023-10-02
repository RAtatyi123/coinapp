package com.example.coinapp.presentation.Coin

import com.example.coinapp.domain.model.Coin.CoinDetail

data class CoinState(
    val isLoading: Boolean = false,
    val coinDetail : CoinDetail? = null,
    val error : String = ""
)
