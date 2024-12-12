package com.ibrahimcanerdogan.jetmovielibraryapp.domain.usecase

import com.ibrahimcanerdogan.jetmovielibraryapp.data.database.favorite.MovieFavoriteEntity
import com.ibrahimcanerdogan.jetmovielibraryapp.domain.repository.MovieRepository
import javax.inject.Inject

class GetMovieFavoriteDataLocalUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {

    suspend fun executeInsert(movieFavoriteEntity: MovieFavoriteEntity) {
        return movieRepository.addFavoriteMovieDataToDatabase(movieFavoriteEntity)
    }

    suspend fun executeDelete(movieFavoriteEntity: MovieFavoriteEntity) {
        return movieRepository.deleteFavoriteMovieDataFromDatabase(movieFavoriteEntity)
    }

    suspend fun executeList(): List<MovieFavoriteEntity> {
        return movieRepository.getFavoriteMoviesDataFromDatabase()
    }
}