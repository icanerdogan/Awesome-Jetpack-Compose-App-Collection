package com.ibrahimcanerdogan.jetmovielibraryapp.domain.model

import com.ibrahimcanerdogan.jetmovielibraryapp.data.network.dto.MovieDetailRateDTO

data class MovieDetailData(
    val movieDataID: String,
    val movieDataTitle: String,
    val movieDataYear: String,
    val movieDataReleased: String,
    val movieDataRuntime: String,
    val movieDataGenre: String,
    val movieDataDirector: String,
    val movieDataWriter: String,
    val movieDataActors: String,
    val movieDataPlot: String,
    val movieDataLanguage: String,
    val movieDataCountry: String,
    val movieDataPoster: String,
    val movieDataRatings: List<MovieDetailRateDTO>,
    val movieDataImdbRating: String,
    val movieDataImdbVotes: String,
)