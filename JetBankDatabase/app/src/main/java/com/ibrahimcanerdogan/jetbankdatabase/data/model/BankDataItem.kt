package com.ibrahimcanerdogan.jetbankdatabase.data.model

import com.google.gson.annotations.SerializedName

data class BankDataItem(
    @SerializedName("ID")
    val bankID: Int? = null,
    @SerializedName("dc_ADRES")
    val bankAddress: String? = null,
    @SerializedName("dc_ADRES_ADI")
    val bankAddressName: String? = null,
    @SerializedName("dc_BANKA_SUBE")
    val bankBranch: String? = null,
    @SerializedName("SerializedName")
    val bankType: String? = null,
    @SerializedName("dc_BANK_KODU")
    val bankCode: String? = null,
    @SerializedName("dc_BOLGE_KOORDINATORLUGU")
    val bankCoordinate: String? = null,
    @SerializedName("dc_EN_YAKIM_ATM")
    val bankAtm: String? = null,
    @SerializedName("dc_ILCE")
    val bankCity: String? = null,
    @SerializedName("dc_SEHIR")
    val bankDistrict: String? = null,
    @SerializedName("dc_ON_OFF_LINE")
    val bankOffLine: String? = null,
    @SerializedName("dc_ON_OFF_SITE")
    val bankOffSite: String? = null,
    @SerializedName("dc_POSTA_KODU")
    val bankPostalCode: String? = null
)