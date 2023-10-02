package com.example.coinapp.data.data_source

import com.example.coinapp.data.data_source.dto.CoinDetailDTO.CoinDetailDTO
import com.example.coinapp.data.data_source.dto.CoinListDTO.CoinListDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CoinGeckoApi {

    //http get запрос

    // запрос на получения списка криптовалют

    @GET("/api/v3/coins/markets?vs_currency=usd&order=market_cap_desc&per_page=100&sparkline=false")
    suspend fun getAlCoins(@Query("page")page: String):CoinListDTO

    // запрос на получения данных о валюте по id

    @GET("/api/v3/coins/{id}")
    suspend fun getCoinBy(@Path("id")id:String):CoinDetailDTO
}