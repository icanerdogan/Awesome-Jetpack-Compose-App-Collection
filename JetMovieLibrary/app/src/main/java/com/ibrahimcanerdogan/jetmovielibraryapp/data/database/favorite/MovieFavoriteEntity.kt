package com.ibrahimcanerdogan.jetmovielibraryapp.data.database.favorite

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ibrahimcanerdogan.jetmovielibraryapp.domain.model.MovieListData

@Entity(tableName = "movie_favorite_table")
data class MovieFavoriteEntity(
    @PrimaryKey
    @ColumnInfo(name = "movie_id") val movieEntityImdbID: String,
    @ColumnInfo(name = "movie_title") val movieEntityTitle: String,
    @ColumnInfo(name = "movie_poster") val movieEntityPoster: String,
    @ColumnInfo(name = "movie_year") val movieEntityYear: String
)

fun MovieFavoriteEntity.toMovieListData() : MovieListData {
    return MovieListData(
        movieSearchImdbID = movieEntityImdbID,
        movieSearchTitle = movieEntityTitle,
        movieSearchPoster = movieEntityPoster,
        movieSearchYear = movieEntityYear
    )
}