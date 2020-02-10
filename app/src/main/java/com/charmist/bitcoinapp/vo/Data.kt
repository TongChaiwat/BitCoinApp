package com.charmist.bitcoinapp.vo

data class Data(
    val base: Base,
    val coins: MutableList<Coin>,
    val stats: Stats
)