package com.ibrahimcanerdogan.cryptoapp.data.model.detail

import com.google.gson.annotations.SerializedName

data class CryptoDetailData(
    @SerializedName("id")
    val detailID: Int?,
    @SerializedName("category")
    val detailCategory: String?,
    @SerializedName("date_added")
    val detailDateAdded: String?,
    @SerializedName("date_launched")
    val detailDateLaunched: Any?,
    @SerializedName("description")
    val detailDescription: String?,
    @SerializedName("is_hidden")
    val isHidden: Int?,
    @SerializedName("logo")
    val detailLogo: String?,
    @SerializedName("name")
    val detailName: String?,
    @SerializedName("notice")
    val detailNotice: String?,
    @SerializedName("platform")
    val detailPlatform: Any?,
    @SerializedName("self_reported_circulating_supply")
    val detailSelfReportedCirculatingSupply: Any?,
    @SerializedName("self_reported_market_cap")
    val detailSelfReportedMarketCap: Any?,
    @SerializedName("self_reported_tags")
    val detailSelfReportedTags: Any?,
    @SerializedName("slug")
    val detailSlug: String?,
    @SerializedName("subreddit")
    val detailSubreddit: String?,
    @SerializedName("symbol")
    val detailSymbol: String?,
    @SerializedName("tag-groups")
    val detailTagGroups: List<String>?,
    @SerializedName("tag-names")
    val detailTagNames: List<String>?,
    @SerializedName("tags")
    val detailTags: List<String>?,
    @SerializedName("twitter_username")
    val detailTwitterUsername: String?,
    @SerializedName("urls")
    val detailUrls: CryptoDetailUrl?
)