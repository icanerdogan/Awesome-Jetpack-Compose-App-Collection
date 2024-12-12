package com.ibrahimcanerdogan.jetmarvelcomicslibrary.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun EmptyListIcon(drawableRes: Int) {
    Surface(
        modifier = Modifier.fillMaxSize().padding(150.dp)
    ) {
        Image(painter = painterResource(drawableRes), contentDescription = null, modifier = Modifier.size(100.dp))
    }
}