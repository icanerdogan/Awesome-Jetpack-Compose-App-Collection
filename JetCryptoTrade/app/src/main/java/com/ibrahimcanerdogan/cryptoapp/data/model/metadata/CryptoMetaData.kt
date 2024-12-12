package com.ibrahimcanerdogan.cryptoapp.data.model.metadata

import com.google.gson.annotations.SerializedName

data class CryptoMetaData(
    @SerializedName("circulating_supply")
    val metaCirculatingSupply: Double,
    @SerializedName("cmc_rank")
    val metaCmcRank: Int,
    @SerializedName("date_added")
    val metaDateAdded: String,
    @SerializedName("id")
    val metaID: Int,
    @SerializedName("last_updated")
    val metaLastUpdated: String,
    @SerializedName("max_supply")
    val metaMaxSupply: Long,
    @SerializedName("name")
    val metaName: String,
    @SerializedName("num_market_pairs")
    val metaNumMarketPairs: Int,
    @SerializedName("quote")
    val metaQuote: CryptoMetaDataQuote,
    @SerializedName("self_reported_circulating_supply")
    val metaSelfReportedCirculatingSupply: Any,
    @SerializedName("self_reported_market_cap")
    val metaSelfReportedMarketCap: Any,
    @SerializedName("slug")
    val metaSlug: String,
    @SerializedName("symbol")
    val metaSymbol: String,
    @SerializedName("tags")
    val metaTags: List<String>,
    @SerializedName("total_supply")
    val metaTotalSupply: Double,
    @SerializedName("tvl_ratio")
    val metaTvlRatio: Any
)