package com.example.coinapp.domain.repository

import com.example.coinapp.data.data_source.dto.CoinDetailDTO.CoinDetailDTO
import com.example.coinapp.data.data_source.dto.CoinListDTO.CoinListDTO

// определение интерфейса для взаимодействия класса и компонента

interface CoinRepository {

    suspend fun getAllCoins(page:String): CoinListDTO

    suspend fun getCoinById(id:String) : CoinDetailDTO

}