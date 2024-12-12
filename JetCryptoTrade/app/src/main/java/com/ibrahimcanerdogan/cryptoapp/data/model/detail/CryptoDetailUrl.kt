package com.ibrahimcanerdogan.cryptoapp.data.model.detail

import com.google.gson.annotations.SerializedName

data class CryptoDetailUrl(
    @SerializedName("announcement")
    val urlAnnouncement: List<String>,
    @SerializedName("chat")
    val urlChat: List<String>,
    @SerializedName("explorer")
    val urlExplorer: List<String>,
    @SerializedName("facebook")
    val urlFacebook: List<Any>,
    @SerializedName("message_board")
    val urlMessageBoard: List<String>,
    @SerializedName("reddit")
    val urlReddit: List<String>,
    @SerializedName("source_code")
    val urlSourceCode: List<String>,
    @SerializedName("technical_doc")
    val urlTechnicalDoc: List<String>,
    @SerializedName("twitter")
    val urlTwitter: List<String>,
    @SerializedName("website")
    val urlWebsite: List<String>
)