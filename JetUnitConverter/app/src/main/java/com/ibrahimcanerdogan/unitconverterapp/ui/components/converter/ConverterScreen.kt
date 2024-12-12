package com.ibrahimcanerdogan.unitconverterapp.ui.components.converter

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.ibrahimcanerdogan.unitconverterapp.data.model.Conversion
import java.math.RoundingMode
import java.text.DecimalFormat

@Composable
fun ConverterScreen(
    list: List<Conversion>,
    selectedConversion: MutableState<Conversion?>,
    inputText: MutableState<String>,
    typedValue: MutableState<String>,
    saveConvert: (String, String) -> Unit
) {
    var toSave by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        ConversionTypeBlock(conversionList = list) {
            selectedConversion.value = it
            typedValue.value = "0.0"
        }

        selectedConversion.value?.let {
            ConversionInputBlock(
                conversion = it,
                inputText = inputText
            ) { input ->
                typedValue.value = input
                toSave = true
            }
        }

        if (typedValue.value != "0.0") {
            val input = typedValue.value.toDouble()
            val multiply = selectedConversion.value!!.conversionMultiplyBy
            val result = input * multiply

            val df = DecimalFormat("#.##")
            df.roundingMode = RoundingMode.DOWN
            val roundedResult = df.format(result)

            val baseType = "${typedValue.value} ${selectedConversion.value!!.conversionBaseType}"
            val convertType = "$roundedResult ${selectedConversion.value!!.conversionConvertType}"

            if (toSave) {
                saveConvert(baseType, convertType)
                toSave = false
            }

            ConversionResultBlock(resultBaseType = baseType, resultConvertType = convertType)
        }
    }
}