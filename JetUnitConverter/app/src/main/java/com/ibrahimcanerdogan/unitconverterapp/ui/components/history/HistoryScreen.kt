package com.ibrahimcanerdogan.unitconverterapp.ui.components.history

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ibrahimcanerdogan.unitconverterapp.data.database.ConversionEntity
import com.ibrahimcanerdogan.unitconverterapp.ui.theme.Color1DarkBlue

@Composable
fun HistoryScreen(
    modifier: Modifier = Modifier,
    historyList: State<List<ConversionEntity>>,
    onCloseTask: (ConversionEntity) -> Unit,
    onClearAllTask: () -> Unit,
) {
    Column {
        if ((historyList.value).isNotEmpty()) {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "History", color = Color1DarkBlue)
                OutlinedButton(onClick = { onClearAllTask.invoke() }) {
                    Text(text = "Clear All", color = Color1DarkBlue)
                }
            }
        }
        HistoryList(
            historyList = historyList,
            onCloseTask = {
                onCloseTask.invoke(it)
            }
        )

    }
}