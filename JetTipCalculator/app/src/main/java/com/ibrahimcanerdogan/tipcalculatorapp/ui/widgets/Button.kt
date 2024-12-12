package com.ibrahimcanerdogan.tipcalculatorapp.ui.widgets

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

val IconButtonSizeModifier = Modifier.size(40.dp)

@Composable
fun RoundIconButton(
    modifier: Modifier = Modifier,
    imageVector: ImageVector,
    onClick: () -> Unit,
    tint: Color = Color.White.copy(alpha = 1f),
    backgroundColor: Color = MaterialTheme.colorScheme.onBackground
) {
    IconButton(
        onClick = { onClick.invoke() },
        modifier = modifier
            .padding(5.dp)
            .clip(CircleShape)
            .then(IconButtonSizeModifier),
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = backgroundColor
        )
    ) {
        Icon(imageVector = imageVector, contentDescription = "Round Button Icon", tint = tint)
    }
}
