package com.ibrahimcanerdogan.jetmovielibraryapp.domain.repository

import com.ibrahimcanerdogan.jetmovielibraryapp.data.database.favorite.MovieFavoriteEntity
import com.ibrahimcanerdogan.jetmovielibraryapp.data.network.dto.MovieDetailDTO
import com.ibrahimcanerdogan.jetmovielibraryapp.data.network.dto.MovieSearchDTO
import com.ibrahimcanerdogan.jetmovielibraryapp.domain.model.MovieDetailData
import com.ibrahimcanerdogan.jetmovielibraryapp.domain.model.MovieListData
import com.ibrahimcanerdogan.jetmovielibraryapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun getAllSearchedMovies(searchText : String) : Flow<Resource<List<MovieListData>>>

    suspend fun getMovieDetailData(movieImdbId : String) : Flow<Resource<MovieDetailData>>

    suspend fun addFavoriteMovieDataToDatabase(movieEntity: MovieFavoriteEntity)

    suspend fun deleteFavoriteMovieDataFromDatabase(movieEntity: MovieFavoriteEntity)

    suspend fun getFavoriteMoviesDataFromDatabase(): List<MovieFavoriteEntity>
}