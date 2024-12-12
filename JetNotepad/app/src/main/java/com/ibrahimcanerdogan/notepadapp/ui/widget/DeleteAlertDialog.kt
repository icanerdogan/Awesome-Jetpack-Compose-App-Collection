package com.ibrahimcanerdogan.notepadapp.ui.widget

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DoDisturb
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun DeleteAlertDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String
) {
    AlertDialog(
        icon = {
            Icon(imageVector = Icons.Default.DoDisturb, contentDescription = "Alert Dialog Icon")
        },
        title = {
            Text(text = dialogTitle)
        },
        text = {
            Text(text = dialogText)
        },
        confirmButton = {
            TextButton(onClick = {
                onConfirmation()
                onDismissRequest()
            }) {
                Text(text = "Onayla")
            }
        },
        dismissButton = {
            TextButton(onClick = {
                onDismissRequest()
            }) {
                Text(text = "Vazgeç")
            }
        },
        onDismissRequest = { onDismissRequest() }
    )
}