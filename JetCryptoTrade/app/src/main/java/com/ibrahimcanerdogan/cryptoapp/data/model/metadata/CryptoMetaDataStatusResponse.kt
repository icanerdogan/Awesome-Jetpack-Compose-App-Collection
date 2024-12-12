package com.ibrahimcanerdogan.cryptoapp.data.model.metadata

import com.google.gson.annotations.SerializedName

data class CryptoMetaDataStatusResponse(
    @SerializedName("credit_count")
    val statusCreditCount: Int,
    @SerializedName("elapsed")
    val statusElapsed: Int,
    @SerializedName("error_code")
    val statusErrorCode: Int,
    @SerializedName("error_message")
    val statusErrorMessage: Any,
    @SerializedName("notice")
    val statusNotice: Any,
    @SerializedName("timestamp")
    val statusTimestamp: String,
    @SerializedName("total_count")
    val statusTotalCount: Int
)