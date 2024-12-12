package com.ibrahimcanerdogan.jetmovielibraryapp.ui.component

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Size
import com.ibrahimcanerdogan.jetmovielibraryapp.data.database.favorite.MovieFavoriteEntity
import com.ibrahimcanerdogan.jetmovielibraryapp.domain.model.MovieDetailData
import com.ibrahimcanerdogan.jetmovielibraryapp.ui.viewmodel.favorite.MovieFavoriteViewModel


@Composable
fun MovieDetailBannerImage(movieDetail: MovieDetailData?, viewModelFavorite: MovieFavoriteViewModel, favoriteList: List<MovieFavoriteEntity>?) {
    val context = LocalContext.current

    var isMovieFavorite by remember { mutableStateOf(false) }

    SideEffect {
        isMovieFavorite = favoriteList?.filter {
            it.movieEntityImdbID == movieDetail?.movieDataID
        }.isNullOrEmpty()
    }

    Box(modifier = Modifier.fillMaxSize()) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(movieDetail?.movieDataPoster)
                .size(Size.ORIGINAL)
                .crossfade(true)
                .build(),
            contentDescription = movieDetail?.movieDataTitle,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(
                    LocalConfiguration.current.screenWidthDp.dp,
                    LocalConfiguration.current.screenHeightDp.dp
                )
                .align(Alignment.TopCenter)
        )
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(movieDetail?.movieDataPoster)
                .size(Size.ORIGINAL)
                .crossfade(true)
                .build(),
            contentDescription = movieDetail?.movieDataTitle,
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .size(
                    LocalConfiguration.current.screenWidthDp.dp,
                    LocalConfiguration.current.screenHeightDp.dp / 2
                )
                .align(Alignment.TopCenter)
        )
        IconButton(
            onClick = {
                viewModelFavorite.addFavorite(
                    MovieFavoriteEntity(
                        movieEntityImdbID = movieDetail?.movieDataID!!,
                        movieEntityTitle = movieDetail.movieDataTitle,
                        movieEntityPoster = movieDetail.movieDataPoster,
                        movieEntityYear = movieDetail.movieDataYear
                    )
                )
                isMovieFavorite = !isMovieFavorite
                Toast.makeText(context, "${movieDetail.movieDataTitle} added to favorites.", Toast.LENGTH_SHORT).show()
                Log.i("TAG", "MovieListItem: ${movieDetail.movieDataTitle}")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Favorite,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .size(40.dp, 40.dp),
                tint = if(!isMovieFavorite) Color.Red else Color.White,
                contentDescription = "Icon Favorite"
            )
        }
    }
}

@Composable
fun MovieDetailDataView(it: MovieDetailData?) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color.Black.copy(0.1f),
                        Color.Black.copy(0.8f),
                        Color.Black.copy(1f),
                    )
                )
            )
            .padding(vertical = 20.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = it?.movieDataTitle ?: "",
            style = MaterialTheme.typography.headlineLarge
        )
        Spacer(modifier = Modifier.height(10.dp))

        Row {
            Text(
                text = "${it?.movieDataGenre} | ",
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = "${it?.movieDataLanguage}",
                style = MaterialTheme.typography.bodySmall
            )
        }
        Text(
            text = "${it?.movieDataRuntime}",
            style = MaterialTheme.typography.bodySmall
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Star,
                tint = Color.White,
                contentDescription = "Movie Detail Star Icon"
            )
            Spacer(modifier = Modifier.width(10.dp))

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "${it?.movieDataImdbRating}",
                    style = MaterialTheme.typography.bodyLarge
                )

                Spacer(modifier = Modifier.width(5.dp))

                Text(
                    text = " (${it?.movieDataImdbVotes})",
                    style = MaterialTheme.typography.bodySmall
                )
            }
            Spacer(modifier = Modifier.width(10.dp))

            Icon(
                imageVector = Icons.Default.Star,
                tint = Color.White,
                contentDescription = "Movie Detail Star Icon"
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Summary",
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = "${it?.movieDataPlot}",
            style = MaterialTheme.typography.bodySmall
        )

        Spacer(modifier = Modifier.height(10.dp))

        Row {
            Text(
                text = "Director: ${it?.movieDataDirector}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Spacer(modifier = Modifier.height(10.dp))

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Actors",
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.width(5.dp))

            Text(
                text = " ${it?.movieDataActors}",
                style = MaterialTheme.typography.bodySmall
            )

        }
    }
}

@Composable
fun MovieDetailLoadingView() {
    Box(modifier = Modifier.fillMaxSize()) {
        CircularProgressIndicator(
            modifier = Modifier
                .fillMaxSize()
                .padding(300.dp)
                .align(Alignment.Center)
        )
    }
}