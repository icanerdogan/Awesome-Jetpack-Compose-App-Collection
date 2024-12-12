package com.ibrahimcanerdogan.jetmovielibraryapp.data.network

import com.ibrahimcanerdogan.jetmovielibraryapp.data.network.dto.MovieDetailDTO
import com.ibrahimcanerdogan.jetmovielibraryapp.data.network.dto.MovieSearchDTO
import com.ibrahimcanerdogan.jetmovielibraryapp.util.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieAPIService {

    @GET("/")
    suspend fun getAllSearchMovieDataFromNetwork(
        @Query("s") searchText : String,
        @Query("apikey") apiKey: String = Constants.API_KEY
    ) : MovieSearchDTO

    @GET("/")
    suspend fun getMovieDetailDataFromNetwork(
        @Query("i") movieImdbID : String,
        @Query("apikey") apiKey: String = Constants.API_KEY
    ) : MovieDetailDTO
}