package com.ibrahimcanerdogan.jetmovielibraryapp.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ibrahimcanerdogan.jetmovielibraryapp.ui.component.MovieDetailBannerImage
import com.ibrahimcanerdogan.jetmovielibraryapp.ui.component.MovieDetailDataView
import com.ibrahimcanerdogan.jetmovielibraryapp.ui.component.MovieDetailLoadingView
import com.ibrahimcanerdogan.jetmovielibraryapp.ui.viewmodel.detail.MovieDetailViewModel
import com.ibrahimcanerdogan.jetmovielibraryapp.ui.viewmodel.favorite.MovieFavoriteViewModel

@Composable
fun MovieDetailScreen(
    viewModel: MovieDetailViewModel = hiltViewModel(),
    viewModelFavorite: MovieFavoriteViewModel
) {
    val state = viewModel.stateDetail.value
    val stateFavorite = viewModelFavorite.favoriteMovies.value

    if (state.stateError.isNullOrEmpty() && !state.stateIsLoading) {
        Box {
            state.stateMovieDetail.let {
                MovieDetailBannerImage(it, viewModelFavorite, stateFavorite)
                MovieDetailDataView(it)
            }
        }
    }

    if (!state.stateError.isNullOrEmpty()) {
        Text(
            text = state.stateError ?: "",
            color = Color.Red,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp)
        )
    }

    if (state.stateIsLoading) {
        MovieDetailLoadingView()
    }
}

