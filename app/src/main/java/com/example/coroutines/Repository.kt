package com.example.coroutines

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.create

class Repository(client:Retrofit) {
    private val apiInterface = client.create(ApiInterface::class.java)

    suspend fun getCurrencyByName(name:String):Response<BitcoinResponce>  = apiInterface.getCryptoByName(name)
}
