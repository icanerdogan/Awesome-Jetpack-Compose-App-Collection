package com.ibrahimcanerdogan.jetmarvelcomicslibrary.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.ibrahimcanerdogan.jetmarvelcomicslibrary.R

@Composable
fun CharacterListImage(
    characterThumbnail: String
) {
    SubcomposeAsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(characterThumbnail)
            .crossfade(true)
            .build(),
        loading = {
            Box(
                modifier = Modifier.padding(10.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        },
        contentDescription = stringResource(R.string.app_name),
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxSize(),
    )
}


@Composable
fun CharacterDetailImage(
    characterThumbnail: String
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(characterThumbnail)
            .crossfade(true)
            .build(),
        placeholder = painterResource(R.drawable.icon_image_search),
        contentDescription = stringResource(R.string.app_name),
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .height(300.dp)
            .width(300.dp)
            .clip(CircleShape),
    )
}