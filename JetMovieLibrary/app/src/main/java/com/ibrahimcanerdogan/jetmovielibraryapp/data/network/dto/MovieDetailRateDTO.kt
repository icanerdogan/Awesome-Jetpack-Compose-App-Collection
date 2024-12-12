package com.ibrahimcanerdogan.jetmovielibraryapp.data.network.dto

import com.google.gson.annotations.SerializedName

data class MovieDetailRateDTO(
    @SerializedName("Source")
    val movieRatingSource: String,
    @SerializedName("Value")
    val movieRatingValue: String
)