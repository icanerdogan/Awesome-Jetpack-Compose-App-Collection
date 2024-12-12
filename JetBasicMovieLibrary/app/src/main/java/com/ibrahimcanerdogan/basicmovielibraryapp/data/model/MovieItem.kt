package com.ibrahimcanerdogan.basicmovielibraryapp.data.model

data class MovieItem(
    val movieImdbID: String,
    val movieComingSoon: Boolean,
    val movieActors: String,
    val movieAwards: String,
    val movieCountry: String,
    val movieDirector: String,
    val movieGenre: String,
    val movieImages: List<String>,
    val movieLanguage: String,
    val movieMetaScore: String,
    val moviePlot: String,
    val moviePoster: String,
    val movieRate: String,
    val movieReleased: String,
    val movieTitle: String,
    val movieType: String,
    val movieWriter: String,
    val movieYear: String,
    val movieImdbRating: String,
    val movieImdbVotes: String
)
