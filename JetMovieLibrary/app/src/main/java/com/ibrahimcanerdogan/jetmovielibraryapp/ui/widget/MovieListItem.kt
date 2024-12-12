package com.ibrahimcanerdogan.jetmovielibraryapp.ui.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Size
import com.ibrahimcanerdogan.jetmovielibraryapp.domain.model.MovieListData

@Composable
fun MovieListItem(
    modifier: Modifier = Modifier,
    movieListData: MovieListData,
    titleFontSize: TextUnit = 30.sp,
    subtitleFontSize: TextUnit = 20.sp,
    isRemoveButton: Boolean = false,
    onItemClick: (MovieListData) -> Unit,
    onRemoveClick: (MovieListData) -> Unit = {}
) {
    Box(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
            .clip(RoundedCornerShape(20.dp))
            .border(1.dp, Color.White, RoundedCornerShape(20.dp))
            .clickable {
                onItemClick(movieListData)
            }
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(movieListData.movieSearchPoster)
                .size(Size.ORIGINAL)
                .crossfade(true)
                .build(),
            contentDescription = movieListData.movieSearchTitle,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(
                    LocalConfiguration.current.screenWidthDp.dp,
                    LocalConfiguration.current.screenHeightDp.dp
                )
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomStart)
                .background(Color.Black.copy(0.5f))
                .padding(10.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = movieListData.movieSearchTitle,
                fontSize = titleFontSize,
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = movieListData.movieSearchYear,
                fontSize = subtitleFontSize,
                style = MaterialTheme.typography.bodySmall
            )
        }

        if (isRemoveButton) {
            IconButton(
                modifier = Modifier.align(Alignment.TopEnd),
                onClick = {
                    onRemoveClick(movieListData)
                }) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    tint = Color.White,
                    contentDescription = "List Item Delete Icon"
                )
            }
        }
    }
}