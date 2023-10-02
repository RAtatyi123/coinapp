package com.example.coinapp.domain.use_case

import com.example.coinapp.domain.model.Coin.CoinDetail
import com.example.coinapp.domain.repository.CoinRepository
import com.example.coinapp.util.ResponseState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CoinDetailUseCase@Inject constructor (
    private val repository: CoinRepository
) {

    //функция принимает Id и возвращает Flow<ResponseState<CoinDetail>> для асинхронного выполнения операции.

    operator fun invoke(id: String): Flow<ResponseState<CoinDetail>> = flow {

        try {
            // это для закгрузки, успешного выполнения и ошибки
            emit(ResponseState.Loading<CoinDetail>())
            val coinDetail = repository.getCoinById(id).toCoinDetail()
            emit(ResponseState.Success<CoinDetail>(coinDetail))
        } catch (e: HttpException) {
            emit(ResponseState.Error<CoinDetail>(e.localizedMessage
                ?: " An Unexpected Error Occurred"))
        } catch (e: IOException) {
            emit(ResponseState.Error<CoinDetail>(message = "Error Occurred"))
        }
    }
}