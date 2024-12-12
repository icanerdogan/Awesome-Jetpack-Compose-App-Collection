package com.ibrahimcanerdogan.jetmovielibraryapp.data.repository.datasource

import com.ibrahimcanerdogan.jetmovielibraryapp.data.database.favorite.MovieFavoriteEntity

interface LocalDataSource {

    suspend fun addFavoriteMovieDataToDatabase(movieEntity: MovieFavoriteEntity)

    suspend fun deleteFavoriteMovieDataFromDatabase(movieEntity: MovieFavoriteEntity)

    suspend fun getFavoriteMoviesDataFromDatabase(): List<MovieFavoriteEntity>

}