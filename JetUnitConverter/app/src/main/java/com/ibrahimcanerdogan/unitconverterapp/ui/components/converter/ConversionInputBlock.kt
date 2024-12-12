package com.ibrahimcanerdogan.unitconverterapp.ui.components.converter

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ibrahimcanerdogan.unitconverterapp.data.model.Conversion
import com.ibrahimcanerdogan.unitconverterapp.ui.theme.Color1Beige
import com.ibrahimcanerdogan.unitconverterapp.ui.theme.Color1Blue

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ConversionInputBlock(
    modifier: Modifier = Modifier,
    conversion: Conversion,
    inputText: MutableState<String>,
    calculate: (String) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(modifier = modifier.padding(10.dp)) {
        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            TextField(
                value = inputText.value,
                onValueChange = {
                    if (it.length <= 15) inputText.value = it
                },
                modifier = modifier.fillMaxWidth(0.60f),
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.None,
                    autoCorrect = true,
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        try {
                            if (inputText.value.isNotEmpty()) {
                                calculate(inputText.value)
                                inputText.value = ""
                            }
                        } catch (e: Exception) {
                            Log.e("Input Block", e.message.toString())
                        }
                        keyboardController?.hide()
                    }
                ),
                maxLines = 1,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color1Beige,
                    focusedIndicatorColor = Color1Blue,
                    unfocusedContainerColor = Color1Beige
                ),
                textStyle = TextStyle(
                    color = Color.Black,
                    fontSize = 18.sp
                )
            )

            Text(
                text = conversion.conversionBaseType,
                fontSize = 18.sp,
                modifier = modifier
                    .padding(10.dp)
                    .fillMaxWidth(0.30f)
            )

            IconButton(
                modifier = modifier.fillMaxWidth(),
                onClick = {
                try {
                    if (inputText.value.isNotEmpty()) {
                        calculate(inputText.value)
                        inputText.value = ""
                    }
                } catch (e: Exception) {
                    Log.e("Input Block", e.message.toString())
                }
            }) {
                Icon(imageVector = Icons.Filled.Send, contentDescription = "Convert Icon")
            }
        }
    }
}