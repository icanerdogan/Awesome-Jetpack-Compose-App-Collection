package com.ibrahimcanerdogan.jetmovielibraryapp.data.repository

import com.ibrahimcanerdogan.jetmovielibraryapp.data.database.favorite.MovieFavoriteEntity
import com.ibrahimcanerdogan.jetmovielibraryapp.data.network.MovieAPIService
import com.ibrahimcanerdogan.jetmovielibraryapp.data.network.mapper.MovieMapper.toMovieDetailData
import com.ibrahimcanerdogan.jetmovielibraryapp.data.network.mapper.MovieMapper.toMovieListData
import com.ibrahimcanerdogan.jetmovielibraryapp.data.repository.datasource.LocalDataSource
import com.ibrahimcanerdogan.jetmovielibraryapp.data.repository.datasource.RemoteDataSource
import com.ibrahimcanerdogan.jetmovielibraryapp.domain.model.MovieDetailData
import com.ibrahimcanerdogan.jetmovielibraryapp.domain.model.MovieListData
import com.ibrahimcanerdogan.jetmovielibraryapp.domain.repository.MovieRepository
import com.ibrahimcanerdogan.jetmovielibraryapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOError
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : MovieRepository {

    override suspend fun getAllSearchedMovies(
        searchText: String
    ): Flow<Resource<List<MovieListData>>> = flow {
        try {
            emit(Resource.Loading())
            val movieList = remoteDataSource.getAllSearchMovieDataFromNetwork(searchText)
            if (movieList.movieResponse.equals("True")) {
                emit(Resource.Success(movieList.toMovieListData()))
            } else {
                emit(Resource.Error("No Movie Found!"))
            }
        } catch (e: IOError) {
            emit(Resource.Error("No Internet Connection!"))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Error!"))
        }
    }

    override suspend fun getMovieDetailData(
        movieImdbID: String
    ): Flow<Resource<MovieDetailData>> = flow {
        try {
            emit(Resource.Loading())
            val movieData = remoteDataSource.getMovieDetailDataFromNetwork(movieImdbID)
            if (movieData.movieDataResponse == "True") {
                emit(Resource.Success(movieData.toMovieDetailData()))
            } else {
                emit(Resource.Error("No Movie Found!"))
            }
        } catch (e: IOError) {
            emit(Resource.Error("No Internet Connection!"))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Error!"))
        }
    }

    override suspend fun addFavoriteMovieDataToDatabase(movieEntity: MovieFavoriteEntity) {
        return localDataSource.addFavoriteMovieDataToDatabase(movieEntity)
    }

    override suspend fun deleteFavoriteMovieDataFromDatabase(movieEntity: MovieFavoriteEntity) {
        return localDataSource.deleteFavoriteMovieDataFromDatabase(movieEntity)
    }

    override suspend fun getFavoriteMoviesDataFromDatabase(): List<MovieFavoriteEntity> {
        return localDataSource.getFavoriteMoviesDataFromDatabase()
    }
}