package com.ibrahimcanerdogan.jetmovielibraryapp.data.repository.datasourceImpl

import com.ibrahimcanerdogan.jetmovielibraryapp.data.database.favorite.MovieFavoriteDAO
import com.ibrahimcanerdogan.jetmovielibraryapp.data.database.favorite.MovieFavoriteEntity
import com.ibrahimcanerdogan.jetmovielibraryapp.data.repository.datasource.LocalDataSource
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val movieFavoriteDao: MovieFavoriteDAO
) : LocalDataSource {

    override suspend fun addFavoriteMovieDataToDatabase(movieEntity: MovieFavoriteEntity) {
        return movieFavoriteDao.insertFavoriteMovieEntityDatabase(movieEntity)
    }

    override suspend fun deleteFavoriteMovieDataFromDatabase(movieEntity: MovieFavoriteEntity) {
        return movieFavoriteDao.deleteFavoriteMovieEntityDatabase(movieEntity)
    }

    override suspend fun getFavoriteMoviesDataFromDatabase(): List<MovieFavoriteEntity> {
        return movieFavoriteDao.getAllFavoriteMovieEntityFromDatabase()
    }
}