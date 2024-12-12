package com.ibrahimcanerdogan.cryptoapp.data.model.metadata

import com.google.gson.annotations.SerializedName

data class CryptoMetaDataResponse(
    @SerializedName("data")
    val `data`: List<CryptoMetaData>,
    @SerializedName("status")
    val status: CryptoMetaDataStatusResponse
)