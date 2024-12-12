package com.ibrahimcanerdogan.cryptoapp.data.model.detail

import com.google.gson.annotations.SerializedName

data class CryptoDetailStatusResponse(
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
    val statusTimestamp: String
)