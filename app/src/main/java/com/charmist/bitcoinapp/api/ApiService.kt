package com.charmist.bitcoinapp.api

import com.charmist.bitcoinapp.vo.BitCoinResult
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @GET("/v1/public/coins")
    fun getCoin(@Query("limit") limit: Int, @Query("offset") offset: Int): Call<BitCoinResult>

    @GET("/v1/public/coins")
    fun getCoinByPrefix(@Query("prefix") limit: String): Call<BitCoinResult>

}