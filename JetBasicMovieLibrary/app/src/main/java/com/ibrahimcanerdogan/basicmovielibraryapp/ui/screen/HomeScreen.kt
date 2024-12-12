package com.ibrahimcanerdogan.basicmovielibraryapp.ui.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ibrahimcanerdogan.basicmovielibraryapp.data.model.MovieItem
import com.ibrahimcanerdogan.basicmovielibraryapp.data.model.getDummyMovie
import com.ibrahimcanerdogan.basicmovielibraryapp.ui.components.MovieCircleImage
import com.ibrahimcanerdogan.basicmovielibraryapp.ui.components.MovieComingSoonImage
import com.ibrahimcanerdogan.basicmovielibraryapp.ui.navigation.MovieScreens
import com.ibrahimcanerdogan.basicmovielibraryapp.ui.theme.Color1Beige
import com.ibrahimcanerdogan.basicmovielibraryapp.ui.theme.Color1Blue
import com.ibrahimcanerdogan.basicmovielibraryapp.ui.theme.Color1Green
import com.ibrahimcanerdogan.basicmovielibraryapp.ui.theme.Color1Pink

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Movies") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color1Beige,
                    titleContentColor = Color.Black
                )
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color1Beige)
                .padding(it)
        ) {
            MovieContent(
                navController = navController
            )
        }
    }
}

@Composable
fun MovieContent(
    navController: NavController,
    movieList: ArrayList<MovieItem> = getDummyMovie()
) {
    LazyColumn {
        items(movieList) {movieItem ->
            MovieRow(movieItem = movieItem) {
                navController.navigate(route = MovieScreens.DetailScreen.name + "/$it")
            }
        }
    }
}

@Preview
@Composable
fun MovieRow(
    modifier: Modifier = Modifier,
    movieItem: MovieItem = getDummyMovie()[0],
    onItemClick: (String) -> Unit = {}
) {
    Card(
        modifier = modifier
            .padding(horizontal = 10.dp, vertical = 5.dp)
            .fillMaxWidth()
            .clickable {
                onItemClick(movieItem.movieImdbID)
            },
        colors = CardDefaults.cardColors(
            containerColor = Color1Blue,
            contentColor = Color.Black
        ),
        shape = RoundedCornerShape(corner = CornerSize(20.dp)),
        elevation = CardDefaults.cardElevation(5.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            MovieRowPosterImage(movieItem = movieItem)

            Column(
                modifier = modifier.padding(10.dp)
            ) {
                MovieRowMainData(movieItem = movieItem)
                MovieExpendableData(movieItem = movieItem)
            }
        }
    }
}

@Composable
fun MovieRowPosterImage(
    modifier: Modifier = Modifier,
    movieItem: MovieItem
) {
    Surface(
        modifier = modifier
            .padding(10.dp)
            .size(100.dp),
        shape = CircleShape,
        shadowElevation = 10.dp
    ) {
        MovieCircleImage(imageUrl = movieItem.movieImages.first())
        if (movieItem.movieComingSoon) {
            MovieComingSoonImage()
        }
    }
}

@Composable
fun MovieRowMainData(movieItem: MovieItem) {
    Text(
        text = movieItem.movieTitle,
        style = MaterialTheme.typography.titleMedium
    )
    Text(
        text = "Director: ${movieItem.movieDirector}",
        style = MaterialTheme.typography.labelMedium
    )
    Text(
        text = "Date: ${movieItem.movieYear}",
        style = MaterialTheme.typography.labelMedium
    )
}

@Composable
fun MovieExpendableData(
    modifier: Modifier = Modifier,
    movieItem: MovieItem
) {
    var expandableState by remember { mutableStateOf(false) }

    AnimatedVisibility(visible = expandableState) {
        Column {
            Divider(
                modifier = modifier
                    .padding(vertical = 5.dp)
                    .border(0.4.dp, Color.Black)
            )
            Text(
                text = "${movieItem.moviePlot}",
                style = MaterialTheme.typography.bodySmall
            )

            Spacer(modifier = modifier.padding(vertical = 3.dp))

            Text(
                text = "${movieItem.movieActors}",
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(modifier = modifier.padding(vertical = 3.dp))

            Text(
                text = "${movieItem.movieImdbRating}",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }

    Surface(
        modifier = modifier
            .clip(CircleShape)
            .padding(top = 10.dp)
            .border(1.dp, Color1Pink, CircleShape),
        color = Color1Green,
        shape = CircleShape
    ) {
        Icon(
            imageVector = if (expandableState) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
            modifier = modifier
                .size(25.dp)
                .clickable {
                    expandableState = !expandableState
                },
            contentDescription = "Expandable Icon Arrow"
        )
    }
}
