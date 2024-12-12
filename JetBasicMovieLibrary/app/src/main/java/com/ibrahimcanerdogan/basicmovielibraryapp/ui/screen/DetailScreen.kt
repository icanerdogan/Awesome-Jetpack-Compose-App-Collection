package com.ibrahimcanerdogan.basicmovielibraryapp.ui.screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ibrahimcanerdogan.basicmovielibraryapp.data.model.MovieItem
import com.ibrahimcanerdogan.basicmovielibraryapp.data.model.getDummyMovie
import com.ibrahimcanerdogan.basicmovielibraryapp.ui.components.MovieRectangleImage
import com.ibrahimcanerdogan.basicmovielibraryapp.ui.theme.Color1Beige

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    movieId: String?
) {
    val context = LocalContext.current
    val detailedMovie = getDummyMovie().first { it.movieImdbID == movieId }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = detailedMovie.movieTitle) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color1Beige,
                    titleContentColor = Color.Black
                ),
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            tint = Color.Black,
                            contentDescription = "Detail Back"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {
                        Toast.makeText(
                            context,
                            "${detailedMovie.movieTitle} added to favorites.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Favorite,
                            tint = Color.Red,
                            contentDescription = "Detail Favorite"
                        )
                    }
                }
            )
        }
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(Color1Beige)
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            MainContent(movieItem = detailedMovie)
        }
    }

}

@Composable
fun MainContent(modifier: Modifier = Modifier, movieItem: MovieItem) {
    MovieDetailData(modifier = modifier, movieItem = movieItem)
    MovieDetailScrollableImage(modifier = modifier, movieItem = movieItem)
}

@Composable
fun MovieDetailData(modifier: Modifier, movieItem: MovieItem) {
    Text(text = movieItem.movieTitle, style = MaterialTheme.typography.headlineLarge)
    Text(text = movieItem.movieYear, style = MaterialTheme.typography.titleLarge)
    Text(text = movieItem.movieDirector, style = MaterialTheme.typography.titleLarge)
    Text(text = movieItem.movieGenre, style = MaterialTheme.typography.titleMedium)
    Text(text = movieItem.movieImdbRating, style = MaterialTheme.typography.titleMedium)
    Text(
        text = movieItem.moviePlot,
        modifier = modifier.padding(10.dp),
        style = MaterialTheme.typography.titleMedium
    )
}

@Composable
fun MovieDetailScrollableImage(modifier: Modifier, movieItem: MovieItem) {
    LazyRow {
        items(movieItem.movieImages) {image ->
            ElevatedCard(
                modifier = modifier
                    .wrapContentSize()
                    .width(LocalConfiguration.current.screenWidthDp.dp)
                    .padding(10.dp),
                elevation = CardDefaults.elevatedCardElevation(5.dp)
            ) {
                MovieRectangleImage(imageUrl = image)
            }
        }
    }
}


