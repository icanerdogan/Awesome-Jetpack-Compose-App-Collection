package com.ibrahimcanerdogan.unitconverterapp.ui.components.history

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ibrahimcanerdogan.unitconverterapp.data.database.ConversionEntity
import com.ibrahimcanerdogan.unitconverterapp.ui.theme.Color1Blue
import com.ibrahimcanerdogan.unitconverterapp.ui.theme.Color1DarkBlue

@Composable
fun HistoryList(
    historyList: State<List<ConversionEntity>>,
    onCloseTask: (ConversionEntity) -> Unit
) {
    LazyColumn(
        reverseLayout = true
    ) {
        items(
            items = historyList.value,
            key = { item -> item.conversionID }
        ) {
            HistoryListItem(
                historyConversionBase = it.conversionBaseType,
                historyConversionConvert = it.conversionConvertType,
                onClose = {
                    onCloseTask.invoke(it)
                }
            )
        }
    }
}

@Composable
fun HistoryListItem(
    modifier: Modifier = Modifier,
    historyConversionBase: String,
    historyConversionConvert: String,
    onClose: () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(vertical = 2.dp)
            .border(
                border = BorderStroke(0.5.dp, Color1DarkBlue),
                shape = RoundedCornerShape(CornerSize(15.dp))
            ),
        shape = RoundedCornerShape(CornerSize(15.dp))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = historyConversionBase,
                    style = TextStyle(
                        color = Color1Blue,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    ),
                    maxLines = 1
                )
                Text(
                    text = historyConversionConvert,
                    style = TextStyle(
                        color = Color1DarkBlue,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    ),
                    maxLines = 1
                )
            }
            IconButton(onClick = { onClose.invoke() }) {
                Icon(imageVector = Icons.Filled.Delete, contentDescription = "Conversion Delete")
            }
        }
    }
}
