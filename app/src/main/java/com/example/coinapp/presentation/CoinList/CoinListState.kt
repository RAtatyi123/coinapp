package com.example.coinapp.presentation.CoinList

import com.example.coinapp.domain.model.Coin.Coin

data class CoinListState(
    val isLoading: Boolean = false,
    val coinList : List<Coin> = emptyList(),
    val error : String = ""
)
