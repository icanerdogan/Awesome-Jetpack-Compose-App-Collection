package com.ibrahimcanerdogan.unitconverterapp.data.model

data class Conversion(
    val conversionID: Int,
    val conversionDescription: String,
    val conversionBaseType: String,
    val conversionConvertType: String,
    val conversionMultiplyBy: Double
)
