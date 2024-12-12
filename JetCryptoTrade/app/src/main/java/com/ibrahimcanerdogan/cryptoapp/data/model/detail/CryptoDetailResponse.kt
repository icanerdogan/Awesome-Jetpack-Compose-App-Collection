package com.ibrahimcanerdogan.cryptoapp.data.model.detail

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import org.json.JSONArray
import org.json.JSONObject

data class CryptoDetailResponse(
    @SerializedName("data")
    val `data`: Any?,
    @SerializedName("status")
    val status: CryptoDetailStatusResponse
)

fun CryptoDetailResponse.parseData(
    cryptoSymbol: String
): CryptoDetailData {
    val gson = Gson()
    val json = gson.toJson(data)
    val jsonObject = JSONObject(json)
    val jsonArray = jsonObject[cryptoSymbol] as JSONArray

    return gson.fromJson(jsonArray.getJSONObject(0).toString(), CryptoDetailData::class.java)
}