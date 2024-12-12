package com.ibrahimcanerdogan.jetmovielibraryapp.ui.viewmodel.home

import com.ibrahimcanerdogan.jetmovielibraryapp.domain.model.MovieListData

data class MovieHomeState(
    val stateIsLoading : Boolean = false,
    val stateMovieList : List<MovieListData> = emptyList(),
    val stateError : String? = null,
    val stateSearch : String? = null
)