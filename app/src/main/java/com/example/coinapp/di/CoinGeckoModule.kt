package com.example.coinapp.di

import com.example.coinapp.data.data_source.CoinGeckoApi
import com.example.coinapp.data.repository.CoinRepositoryImpl
import com.example.coinapp.domain.repository.CoinRepository
import com.example.coinapp.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object CoinGeckoModule {
    @Provides
    @Singleton
    fun provideCoinGekoApi(): CoinGeckoApi {
        return  Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CoinGeckoApi::class.java)

    }

    //функция принимает CoinGeckoApi
    //как параметр и использует его для создания экземпляра CoinRepositoryImpl.


    @Provides
    @Singleton
    fun provideCoinGeckoRepository(api: CoinGeckoApi):CoinRepository {
        return CoinRepositoryImpl(api)
    }

}