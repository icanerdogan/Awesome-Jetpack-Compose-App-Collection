package com.ibrahimcanerdogan.jetmovielibraryapp.ui.viewmodel.detail

import com.ibrahimcanerdogan.jetmovielibraryapp.domain.model.MovieDetailData

data class MovieDetailState(
    val stateIsLoading : Boolean = false,
    val stateMovieDetail : MovieDetailData? = null,
    val stateError : String? = null,
)