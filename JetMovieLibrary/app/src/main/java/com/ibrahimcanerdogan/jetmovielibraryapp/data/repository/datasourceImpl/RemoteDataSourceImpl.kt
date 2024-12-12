package com.ibrahimcanerdogan.jetmovielibraryapp.data.repository.datasourceImpl

import com.ibrahimcanerdogan.jetmovielibraryapp.data.network.MovieAPIService
import com.ibrahimcanerdogan.jetmovielibraryapp.data.network.dto.MovieDetailDTO
import com.ibrahimcanerdogan.jetmovielibraryapp.data.network.dto.MovieSearchDTO
import com.ibrahimcanerdogan.jetmovielibraryapp.data.repository.datasource.RemoteDataSource
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val apiService: MovieAPIService
) : RemoteDataSource {

    override suspend fun getAllSearchMovieDataFromNetwork(searchText: String): MovieSearchDTO {
        return apiService.getAllSearchMovieDataFromNetwork(searchText)
    }

    override suspend fun getMovieDetailDataFromNetwork(movieImdbID: String): MovieDetailDTO {
        return apiService.getMovieDetailDataFromNetwork(movieImdbID)
    }
}