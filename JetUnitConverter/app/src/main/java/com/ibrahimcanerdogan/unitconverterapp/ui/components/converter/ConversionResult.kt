package com.ibrahimcanerdogan.unitconverterapp.ui.components.converter

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ibrahimcanerdogan.unitconverterapp.ui.theme.Color1Blue
import com.ibrahimcanerdogan.unitconverterapp.ui.theme.Color1DarkBlue

@Preview
@Composable
fun ConversionResultBlock(
    modifier: Modifier = Modifier,
    resultBaseType: String = "1 lbs",
    resultConvertType: String = "0.4525 kg"
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp),
        shape = RoundedCornerShape(CornerSize(10.dp)),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(
            modifier = modifier.padding(10.dp).align(Alignment.CenterHorizontally),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = resultBaseType,
                style = TextStyle(
                    color = Color1Blue,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            Text(
                text = resultConvertType, style = TextStyle(
                    color = Color1DarkBlue,
                    fontSize = 20.sp, fontWeight = FontWeight.Bold
                )
            )
        }
    }

}