package com.ibrahimcanerdogan.cryptoapp.data.model.metadata

import com.google.gson.annotations.SerializedName

data class CryptoMetaDataQuote(
    @SerializedName("USD")
    val quoteDollar: CryptoMetaDataDollar
)