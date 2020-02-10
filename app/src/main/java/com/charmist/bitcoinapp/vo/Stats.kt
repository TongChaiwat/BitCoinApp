package com.charmist.bitcoinapp.vo

data class Stats(
    val base: String,
    val limit: Int,
    val offset: Int,
    val order: String,
    val total: Int,
    val total24hVolume: Double,
    val totalExchanges: Int,
    val totalMarketCap: Double,
    val totalMarkets: Int
)