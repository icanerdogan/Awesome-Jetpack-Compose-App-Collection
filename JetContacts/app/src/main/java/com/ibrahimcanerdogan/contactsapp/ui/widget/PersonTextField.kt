package com.ibrahimcanerdogan.contactsapp.ui.widget

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun PersonOutlinedTextField(
    valueText: String,
    onValueChange: (String) -> Unit,
    labelText: String,
    placeholderText: String,
    keyboardType: KeyboardType
) {
    OutlinedTextField(
        value = valueText,
        onValueChange = {
            onValueChange(it)
        },
        leadingIcon = {
            Icon(imageVector = Icons.Default.Person, contentDescription = "Person Leading Icon")
        },
        trailingIcon = {
            Icon(imageVector = Icons.Default.Edit, contentDescription = "Person Trailing Icon")
        },
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        label = {
            Text(text = labelText)
        },
        placeholder = {
            Text(text = placeholderText)
        }
    )
}