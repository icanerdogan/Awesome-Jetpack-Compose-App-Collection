package com.ibrahimcanerdogan.basicmovielibraryapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import coil.transform.CircleCropTransformation


@Composable
fun MovieCircleImage(imageUrl: String) {
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .size(Size.ORIGINAL)
            .transformations(CircleCropTransformation())
            .crossfade(true)
            .build()
    )

    Image(painter = painter, contentDescription = "Movie Circle Image")
}

@Composable
fun MovieRectangleImage(imageUrl: String) {
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .size(Size.ORIGINAL)
            .crossfade(true)
            .build()
    )

    Image(painter = painter, contentDescription = "Movie Rectangle Image")
}

@Composable
fun MovieComingSoonImage() {
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data("https://cdn-icons-png.flaticon.com/128/8089/8089442.png")
            .crossfade(true)
            .build()
    )

    Image(
        painter = painter,
        modifier = Modifier.padding(20.dp),
        alignment = Alignment.Center,
        contentDescription = "Movie Coming Soon Image"
    )
}