package com.ibrahimcanerdogan.contactsapp.ui.screen

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.google.gson.Gson
import com.ibrahimcanerdogan.contactsapp.data.model.Person
import com.ibrahimcanerdogan.contactsapp.ui.viewmodel.PersonViewModel
import com.ibrahimcanerdogan.contactsapp.ui.widget.SwipeToDeleteContainer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonMainScreen(
    navController: NavController
) {
    val context = LocalContext.current

    val viewModel: PersonViewModel = viewModel()
    val personList = viewModel.personList.observeAsState(listOf())

    val isSearchState = remember { mutableStateOf(false) }
    val searchText = remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    if (isSearchState.value) {
                        TextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = searchText.value,
                            onValueChange = {
                                searchText.value = it
                                viewModel.searchPersonData(it)
                            },
                            textStyle = TextStyle(fontSize = 16.sp),
                            label = { Text(text = "Search") },
                            colors = TextFieldDefaults.colors(
                                focusedTextColor = Color.Black,
                                unfocusedIndicatorColor = Color.LightGray,
                                focusedContainerColor = Color.Transparent,
                                unfocusedContainerColor = Color.Transparent,
                                disabledContainerColor = Color.Transparent
                            ),
                            maxLines = 1,
                            singleLine = true
                        )
                    } else {
                        Text(text = "Contacts", fontSize = 20.sp)
                    }
                },
                actions = {
                    if (isSearchState.value) {
                        IconButton(onClick = {
                            isSearchState.value = false
                            searchText.value = ""
                            viewModel.getAllPersonData()
                        }) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Search Close Icon"
                            )
                        }
                    } else {
                        IconButton(onClick = {
                            isSearchState.value = true
                        }) {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "Search Icon"
                            )
                        }
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate("register")
                },
                containerColor = Color.Blue
            ) {
                Icon(imageVector = Icons.Default.Add, tint = Color.White, contentDescription = "Person Add Icon")
            }
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier.padding(contentPadding)
        ) {
            LazyColumn {
                items(
                    items = personList.value,
                    key = { it.personID }
                ) {
                    PersonListRow(
                        selectedPerson = it,
                        context = context,
                        viewModel = viewModel,
                        navController = navController
                    )
                }
            }
        }
    }
}

@Composable
fun PersonListRow(
    context: Context,
    selectedPerson: Person,
    viewModel: PersonViewModel,
    navController: NavController
) {
    SwipeToDeleteContainer(
        item = selectedPerson,
        onDelete = {
        viewModel.deletePersonData(it.personID)
    }) {
        Card(
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 5.dp)
                .fillMaxWidth()
                .height(70.dp)
                .border(0.5.dp, Color.LightGray, RoundedCornerShape(20.dp)),
            shape = RoundedCornerShape(20.dp)
        ) {
               Row(
                   modifier = Modifier
                       .fillMaxSize()
                       .clickable {
                           val personJson = Gson().toJson(selectedPerson)
                           navController.navigate("detail/${personJson}")
                       },
                   verticalAlignment = Alignment.CenterVertically,
                   horizontalArrangement = Arrangement.SpaceBetween
               ) {
                   Column {
                       Text(
                           text = "${selectedPerson.personName} ${selectedPerson.personSurname}",
                           modifier = Modifier.padding(start = 10.dp),
                           style = MaterialTheme.typography.bodyLarge,
                           color = Color.Black
                       )
                       Text(
                           text = "${selectedPerson.personPhoneNumber}",
                           modifier = Modifier.padding(start = 10.dp),
                           style = MaterialTheme.typography.bodyMedium,
                           color = Color.Black
                       )
                   }

                   IconButton(
                       modifier = Modifier.padding(10.dp),
                       onClick = {
                           val intent = Intent(Intent.ACTION_DIAL).apply {
                               data = Uri.parse("tel:${selectedPerson.personPhoneNumber}")
                           }
                           startActivity(context, intent, null)
                       }
                   ) {
                       Icon(imageVector = Icons.Default.Call, contentDescription = "Call Icon")
                   }
               }
        }
    }
}