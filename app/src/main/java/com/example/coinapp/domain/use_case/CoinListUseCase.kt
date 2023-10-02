package com.example.coinapp.domain.use_case

import com.example.coinapp.domain.model.Coin.Coin
import com.example.coinapp.domain.repository.CoinRepository
import com.example.coinapp.util.ResponseState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CoinListUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(page:String): Flow<ResponseState<List<Coin>>> = flow {

        try {
            emit(ResponseState.Loading<List<Coin>>())
            val coins = repository.getAllCoins(page).map {
                it.toCoin()
            }
            emit(ResponseState.Success<List<Coin>>(coins))
        }
        catch (e: HttpException) {
            emit(ResponseState.Error<List<Coin>>(e.localizedMessage ?: " An Unexpected Error Occurred"))
        }
        catch (e: IOException){
            emit(ResponseState.Error<List<Coin>>(message = "Error Occurred"))
        }

    }
}