package com.ibrahimcanerdogan.jetmovielibraryapp.data.repository.datasource

import com.ibrahimcanerdogan.jetmovielibraryapp.data.network.dto.MovieDetailDTO
import com.ibrahimcanerdogan.jetmovielibraryapp.data.network.dto.MovieSearchDTO

interface RemoteDataSource {

    suspend fun getAllSearchMovieDataFromNetwork(searchText: String): MovieSearchDTO

    suspend fun getMovieDetailDataFromNetwork(movieImdbID: String) : MovieDetailDTO
}