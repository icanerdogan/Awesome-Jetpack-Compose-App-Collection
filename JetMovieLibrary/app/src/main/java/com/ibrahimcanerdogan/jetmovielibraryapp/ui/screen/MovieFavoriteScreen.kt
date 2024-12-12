package com.ibrahimcanerdogan.jetmovielibraryapp.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Coffee
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ibrahimcanerdogan.jetmovielibraryapp.ui.component.MovieFavoriteListView
import com.ibrahimcanerdogan.jetmovielibraryapp.ui.viewmodel.favorite.MovieFavoriteViewModel

@Composable
fun MovieFavoriteScreen(
    navController: NavController,
    viewModel: MovieFavoriteViewModel
) {
    val favoriteMovies = viewModel.favoriteMovies.observeAsState()

    if (favoriteMovies.value.isNullOrEmpty()) {
        Box(modifier = Modifier.fillMaxSize()) {
            Icon(
                imageVector = Icons.Default.Coffee,
                modifier = Modifier
                    .size(200.dp)
                    .align(Alignment.Center),
                tint = Color.White,
                contentDescription = "Empty Favorite Icon"
            )
        }
    } else {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
                .background(MaterialTheme.colorScheme.background)
        ) {
            MovieFavoriteListView(
                navController,
                viewModel,
                favoriteMovies.value ?: emptyList()
            )
        }
    }
}