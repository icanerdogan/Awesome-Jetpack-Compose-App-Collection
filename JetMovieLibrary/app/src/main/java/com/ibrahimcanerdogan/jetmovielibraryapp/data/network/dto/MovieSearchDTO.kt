package com.ibrahimcanerdogan.jetmovielibraryapp.data.network.dto

import com.google.gson.annotations.SerializedName

data class MovieSearchDTO(
    @SerializedName("Search")
    val movieSearch: List<MovieSearchDataDTO>,
    @SerializedName("totalResults")
    val movieTotalResults: String,
    @SerializedName("Response")
    val movieResponse: String
)