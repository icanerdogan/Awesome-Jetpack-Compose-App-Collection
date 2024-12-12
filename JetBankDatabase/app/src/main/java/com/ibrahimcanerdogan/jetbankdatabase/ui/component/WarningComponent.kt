package com.ibrahimcanerdogan.jetbankdatabase.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BrokenImage
import androidx.compose.material.icons.filled.NotInterested
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ErrorImage() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            imageVector = Icons.Default.BrokenImage,
            contentDescription = "Error Image",
            modifier = Modifier.size(50.dp)
        )
    }
}

@Composable
fun NoDataImage() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            imageVector = Icons.Default.NotInterested,
            contentDescription = "No Data Image",
            modifier = Modifier.size(50.dp)
        )
    }
}