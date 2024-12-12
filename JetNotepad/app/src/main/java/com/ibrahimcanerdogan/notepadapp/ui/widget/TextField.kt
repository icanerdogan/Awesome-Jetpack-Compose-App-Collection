package com.ibrahimcanerdogan.notepadapp.ui.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign

@Composable
fun NoteInputText(
    modifier: Modifier = Modifier,
    text: String,
    label: String,
    onTextChange: (String) -> Unit,
    maxLine: Int = 1,
    maxChar: Int,
    onImeAction: () -> Unit = {}
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    OutlinedTextField(
        value = text,
        onValueChange = onTextChange,
        maxLines = maxLine,
        label = {
            Text(text = label)
        },
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(
            onDone = {
                onImeAction
                keyboardController?.hide()
            }
        ),
        supportingText =  {
            Text(
                text = "${text.length} / $maxChar",
                modifier= Modifier.fillMaxWidth(),
                textAlign = TextAlign.End
            )
        },
        modifier = modifier.background(Color.Transparent)
    )
}