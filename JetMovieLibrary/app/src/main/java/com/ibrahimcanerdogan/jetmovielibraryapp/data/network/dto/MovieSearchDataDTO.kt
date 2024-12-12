package com.ibrahimcanerdogan.jetmovielibraryapp.data.network.dto

import com.google.gson.annotations.SerializedName

data class MovieSearchDataDTO(
    @SerializedName("Title")
    val movieSearchTitle: String,
    @SerializedName("Year")
    val movieSearchYear: String,
    @SerializedName("imdbID")
    val movieSearchImdbID: String,
    @SerializedName("Type")
    val movieSearchType: String,
    @SerializedName("Poster")
    val movieSearchPoster: String
)
