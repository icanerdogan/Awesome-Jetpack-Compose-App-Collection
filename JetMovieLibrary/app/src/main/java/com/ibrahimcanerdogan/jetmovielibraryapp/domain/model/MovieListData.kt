package com.ibrahimcanerdogan.jetmovielibraryapp.domain.model

import com.ibrahimcanerdogan.jetmovielibraryapp.data.database.favorite.MovieFavoriteEntity

data class MovieListData(
    val movieSearchTitle: String,
    val movieSearchYear: String,
    val movieSearchImdbID: String,
    val movieSearchPoster: String
)

fun MovieListData.toMovieFavoriteEntity(): MovieFavoriteEntity{
    return MovieFavoriteEntity(
        movieEntityTitle = movieSearchTitle,
        movieEntityYear = movieSearchYear,
        movieEntityImdbID = movieSearchImdbID,
        movieEntityPoster = movieSearchPoster
    )
}