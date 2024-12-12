package com.ibrahimcanerdogan.jetmarvelcomicslibrary.ui.screen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.ibrahimcanerdogan.jetmarvelcomicslibrary.R
import com.ibrahimcanerdogan.jetmarvelcomicslibrary.data.connectivity.ConnectivityObservable
import com.ibrahimcanerdogan.jetmarvelcomicslibrary.data.model.CharacterResponse
import com.ibrahimcanerdogan.jetmarvelcomicslibrary.data.network.NetworkResult
import com.ibrahimcanerdogan.jetmarvelcomicslibrary.ui.component.CharacterListImage
import com.ibrahimcanerdogan.jetmarvelcomicslibrary.ui.component.EmptyListIcon
import com.ibrahimcanerdogan.jetmarvelcomicslibrary.ui.navigation.Destination
import com.ibrahimcanerdogan.jetmarvelcomicslibrary.ui.viewmodel.CharacterListViewModel
import com.ibrahimcanerdogan.jetmarvelcomicslibrary.utils.Constants

@Composable
fun CharacterListScreen(
    navController: NavHostController,
    characterListViewModel: CharacterListViewModel
) {
    val result by characterListViewModel.result.collectAsState()
    val text by characterListViewModel.queryText.collectAsState()
    val networkAvailable by characterListViewModel.networkAvailable.observe()
        .collectAsState(ConnectivityObservable.Status.Available)

    val keyboardController = LocalSoftwareKeyboardController.current

    Scaffold { paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize().padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (networkAvailable == ConnectivityObservable.Status.Unavailable) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Red),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Network unavailable",
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }

            OutlinedTextField(
                value = text,
                onValueChange = characterListViewModel::onQueryUpdate,
                label = { Text(text = "Character Search") },
                placeholder = { Text(text = "What is the hero name?") },
                singleLine = true,
                maxLines = 1,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(onSearch = {
                    keyboardController?.hide()
                }),
                trailingIcon = {
                    Icon(Icons.Default.Close, contentDescription = null, modifier = Modifier.clickable {
                        characterListViewModel.onQueryUpdate("")
                    })
                },
                modifier = Modifier.padding(10.dp).fillMaxWidth().wrapContentHeight()
            )

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                when (result) {
                    is NetworkResult.Initial -> {
                        EmptyListIcon(R.drawable.icon_image_search)
                    }

                    is NetworkResult.Success -> {
                        if (!result.data?.responseData?.results.isNullOrEmpty() || text.isNotEmpty()) {
                            ShowCharactersList(result, navController)
                        } else Surface {  }
                    }

                    is NetworkResult.Loading -> {
                        CircularProgressIndicator(
                            modifier = Modifier.size(78.dp),
                            color = Color.White,
                            strokeWidth = 10.dp,
                            trackColor = Color.Green,
                            strokeCap = StrokeCap.Butt
                        )
                    }

                    is NetworkResult.Error -> {
                        if (networkAvailable == ConnectivityObservable.Status.Unavailable) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color.Red),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = result.message ?: "Unknown Error",
                                    fontWeight = FontWeight.Normal,
                                    color = Color.White,
                                    modifier = Modifier.padding(16.dp)
                                )
                            }
                        } else Surface {  }

                    }
                }
            }

        }
    }

}

@Composable
fun ShowCharactersList(
    result: NetworkResult<CharacterResponse>,
    navController: NavHostController
) {
    result.data?.responseData?.results?.let { characters ->
        val newCharactersList = characters.filterNot {
            it.resultThumbnail?.path == Constants.NOTE_IMAGE_URL
        }

        LazyVerticalGrid(
            modifier = Modifier.fillMaxSize(),
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.Start
        ) {
            items(newCharactersList) { character ->
                val imageUrl = character.resultThumbnail?.path + "." + character.resultThumbnail?.extension
                val title = character.resultName
                val description = character.resultDescription
                val context = LocalContext.current
                val id = character.resultId

                if(character.resultThumbnail?.path != Constants.NOTE_IMAGE_URL) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .padding(horizontal = 5.dp, vertical = 5.dp)
                            .border(1.dp, Color.Black, RoundedCornerShape(10.dp))
                            .clip(RoundedCornerShape(10.dp))
                            .clickable {
                                if (character.resultId != null) navController.navigate(Destination.CharacterDetail.createRoute(id))
                                else Toast.makeText(context, "Character id is null", Toast.LENGTH_SHORT).show()
                            }
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.BottomCenter
                        ) {
                            CharacterListImage(imageUrl)
                            Text(
                                title?: "",
                                modifier = Modifier.fillMaxWidth().height(50.dp).background(Color.Black.copy(0.5f)).padding(5.dp),
                                style = TextStyle(
                                    color = Color.White,
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 16.sp
                                )
                            )

                        }
                    }
                }
            }

            result.data.responseAttributionText?.let {
                item(span = {
                    GridItemSpan(maxLineSpan)
                }) {
                    if (characters.isNotEmpty()) AttributionText(text = it)
                }
            }
        }
    }
}

@Composable
fun AttributionText(text: String) {
    Text(text = text, modifier = Modifier.padding(start = 8.dp, top = 4.dp), fontSize = 12.sp)
}