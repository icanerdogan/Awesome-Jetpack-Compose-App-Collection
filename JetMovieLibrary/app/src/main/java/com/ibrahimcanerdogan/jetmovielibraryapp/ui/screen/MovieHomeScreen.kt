package com.ibrahimcanerdogan.jetmovielibraryapp.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EmojiPeople
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ibrahimcanerdogan.jetmovielibraryapp.ui.component.MovieHorizontalPagerView
import com.ibrahimcanerdogan.jetmovielibraryapp.ui.component.MovieSearchBar
import com.ibrahimcanerdogan.jetmovielibraryapp.ui.viewmodel.home.MovieHomeEvent
import com.ibrahimcanerdogan.jetmovielibraryapp.ui.viewmodel.home.MovieHomeViewModel
import kotlinx.coroutines.delay

@Composable
fun MovieHomeScreen(
    navController: NavController,
    viewModel: MovieHomeViewModel = hiltViewModel()
) {
    var showEmptyResult by remember { mutableStateOf(false) }
    val movieDataState = viewModel.state.value

    // Post delayed action to show the icon after 1 second
    LaunchedEffect(null) {
        delay(1000)
        if (movieDataState.stateMovieList.isEmpty()) {
            showEmptyResult = true
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
    ) {
        Column {
            MovieSearchBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, start = 20.dp, end = 20.dp),
                hint = "Search Movie",
                onSearch = {
                    viewModel.onEvent(MovieHomeEvent.SearchEvent(it))
                })

            if (movieDataState.stateMovieList.isNotEmpty()) {
                MovieHorizontalPagerView(
                    movieDataState,
                    navController
                )
            }

            if (showEmptyResult) {
                Icon(
                    imageVector = Icons.Default.EmojiPeople,
                    modifier = Modifier.fillMaxSize(),
                    contentDescription = "Empty Result Icon"
                )
            }
        }
    }
}