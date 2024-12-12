package com.ibrahimcanerdogan.cryptoapp.data.model.metadata

import com.google.gson.annotations.SerializedName

data class CryptoMetaDataDollar(
    @SerializedName("fully_diluted_market_cap")
    val dollarFullyDilutedMarketCap: Double,
    @SerializedName("last_updated")
    val dollarLastUpdated: String,
    @SerializedName("market_cap")
    val dollarMarketCap: Double,
    @SerializedName("market_cap_dominance")
    val dollarMarketCapDominance: Double,
    @SerializedName("percent_change_1h")
    val dollarPercentChange1H: Double,
    @SerializedName("percent_change_24h")
    val dollarPercentChange24H: Double,
    @SerializedName("percent_change_30d")
    val dollarPercentChange30D: Double,
    @SerializedName("percent_change_60d")
    val dollarPercentChange60D: Double,
    @SerializedName("percent_change_7d")
    val dollarPercentChange7D: Double,
    @SerializedName("percent_change_90d")
    val dollarPercentChange90D: Double,
    @SerializedName("price")
    val dollarPrice: Double,
    @SerializedName("tvl")
    val dollarTvl: Any,
    @SerializedName("volume_24h")
    val dollarVolume24H: Double,
    @SerializedName("volume_change_24h")
    val dollarVolumeChange24H: Double
)