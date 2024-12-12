package com.ibrahimcanerdogan.contactsapp.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.ibrahimcanerdogan.contactsapp.ui.viewmodel.PersonViewModel
import com.ibrahimcanerdogan.contactsapp.ui.widget.PersonOutlinedTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonRegisterScreen(
    navController: NavController
) {

    val viewModel: PersonViewModel = viewModel()
    val localFocusManager = LocalFocusManager.current

    val tfPersonName = remember { mutableStateOf("") }
    val tfPersonSurname = remember { mutableStateOf("") }
    val tfPersonPhoneNumber = remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = "Contact Register", fontSize = 20.sp)
            })
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            PersonOutlinedTextField(
                valueText = tfPersonName.value,
                onValueChange = {
                   tfPersonName.value = it.trim()
                },
                labelText = "Contact Name",
                placeholderText = "",
                keyboardType = KeyboardType.Text
            )
            PersonOutlinedTextField(
                valueText = tfPersonSurname.value,
                onValueChange = {
                    tfPersonSurname.value = it.trim()
                },
                labelText = "Contact Surname",
                placeholderText = "",
                keyboardType = KeyboardType.Text
            )
            PersonOutlinedTextField(
                valueText = tfPersonPhoneNumber.value,
                onValueChange = {
                    tfPersonPhoneNumber.value = it.trim()
                },
                labelText = "Contact Phone Number",
                placeholderText = "",
                keyboardType = KeyboardType.Phone
            )

            Button(onClick = {
                viewModel.registerPersonData(
                    tfPersonName.value,
                    tfPersonSurname.value,
                    tfPersonPhoneNumber.value
                )
                navController.popBackStack()
                localFocusManager.clearFocus()
            }) {
                Text(text = "REGISTER")
            }
        }
    }
}