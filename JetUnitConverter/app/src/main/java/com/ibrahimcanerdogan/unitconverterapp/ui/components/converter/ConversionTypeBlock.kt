package com.ibrahimcanerdogan.unitconverterapp.ui.components.converter

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.ibrahimcanerdogan.unitconverterapp.data.model.Conversion

@Composable
fun ConversionTypeBlock(
    modifier: Modifier = Modifier,
    conversionList: List<Conversion>,
    convert: (Conversion) -> Unit
) {
    var displayingText by rememberSaveable { mutableStateOf("Select the Conversion Type") }
    var textFieldSize by remember { mutableStateOf(Size.Zero) }
    var expanded by remember { mutableStateOf(false) }

    val icon = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown

    Column {
        OutlinedTextField(
            value = displayingText,
            onValueChange = {
                displayingText = it
            },
            textStyle = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold),
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned {
                    textFieldSize = it.size.toSize()
                },
            label = {
                Text(text = "Conversion Type")
            },
            trailingIcon = {
                Icon(
                    imageVector = icon,
                    modifier = modifier.clickable {
                        expanded = !expanded
                    },
                    contentDescription = "Conversion Type Icon"
                )
            },
            readOnly = true
        )

        DropdownMenu(
            modifier = modifier.width(with(LocalDensity.current) {
                textFieldSize.width.toDp()
            }),
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }) {
            conversionList.forEach {
                DropdownMenuItem(
                    text = {
                        Text(
                            text = it.conversionDescription,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    },
                    onClick = {
                        displayingText = it.conversionDescription
                        expanded = false
                        convert(it)
                    }
                )
            }
        }
    }
}