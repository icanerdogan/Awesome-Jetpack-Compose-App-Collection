package com.ibrahimcanerdogan.calculatorapp.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.ibrahimcanerdogan.calculatorapp.ui.viewmodel.CalculatorState

@Composable
fun CalculatorResultText(state: CalculatorState) {
    Column {
        Text(
            text = state.number1 + (state.operation?.symbol ?: ""),
            textAlign = TextAlign.End,
            modifier = Modifier.fillMaxWidth(),
            fontWeight = FontWeight.Light,
            fontSize = 70.sp,
            color = MaterialTheme.colorScheme.onSurface,
            maxLines = 1
        )
        if (state.number2.isNotEmpty()) {
            Text(
                text = state.number2,
                textAlign = TextAlign.End,
                modifier = Modifier.fillMaxWidth(),
                fontWeight = FontWeight.Light,
                fontSize = 70.sp,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 1
            )
        }
        if (state.result.isNotEmpty()) {
            Text(
                text = "= ${state.result}",
                textAlign = TextAlign.End,
                modifier = Modifier.fillMaxWidth(),
                fontWeight = FontWeight.Light,
                fontSize = 50.sp,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 1
            )
        }
    }
}