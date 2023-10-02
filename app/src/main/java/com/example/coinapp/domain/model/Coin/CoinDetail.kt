package com.example.coinapp.domain.model.Coin


// это для данных о крипте
data class CoinDetail (
    val name: String,
    val image: String,
    val market_cap : Double,
    val price : Double,
    val price_percentage_change: Double,
    val low_price: Double,
    val high_price: Double,
    val description: String
    )
