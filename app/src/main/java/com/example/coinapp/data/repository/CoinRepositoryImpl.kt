package com.example.coinapp.data.repository

import com.example.coinapp.data.data_source.CoinGeckoApi
import com.example.coinapp.data.data_source.dto.CoinDetailDTO.CoinDetailDTO
import com.example.coinapp.data.data_source.dto.CoinListDTO.CoinListDTO
import com.example.coinapp.domain.repository.CoinRepository
import javax.inject.Inject


// тут  реализация интерфейса CoinRepository


// @Inject указывает на то что зависимость CoinGeckoApi инджекнуться в этот класс .
class CoinRepositoryImpl @Inject constructor(
    private val coinApi : CoinGeckoApi
) :CoinRepository{

    // метод выполнения запроса для криптовалюты
    override suspend fun getAllCoins(page: String): CoinListDTO {
        return coinApi.getAlCoins(page=page)
    }
    // метод выполнения запроса для данных криптовалюты по id
    override suspend fun getCoinById(id: String): CoinDetailDTO {
        return coinApi.getCoinBy(id = id)
    }

}