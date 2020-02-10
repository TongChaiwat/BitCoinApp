package com.charmist.bitcoinapp.vo

data class Coin(
    val allTimeHigh: AllTimeHigh,
    val approvedSupply: Boolean,
    val change: Double,
    val circulatingSupply: Double,
    val color: String,
    val confirmedSupply: Boolean,
    val description: String,
    val firstSeen: Long,
    val history: List<String>,
    val iconType: String,
    val iconUrl: String,
    val id: Int,
    val links: List<Link>,
    val marketCap: Double,
    val name: String,
    val numberOfExchanges: Int,
    val numberOfMarkets: Int,
    val penalty: Boolean,
    val price: String,
    val rank: Int,
    val slug: String,
    val socials: List<Social>,
    val symbol: String,
    val totalSupply: Double,
    val type: String,
    val uuid: String,
    val volume: Double,
    val websiteUrl: String
)