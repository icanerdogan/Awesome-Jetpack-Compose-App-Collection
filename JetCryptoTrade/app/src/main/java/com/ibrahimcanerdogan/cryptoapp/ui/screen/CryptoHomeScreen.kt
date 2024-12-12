package com.ibrahimcanerdogan.cryptoapp.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Autorenew
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ibrahimcanerdogan.cryptoapp.data.model.metadata.CryptoMetaData
import com.ibrahimcanerdogan.cryptoapp.ui.theme.Color1DarkBlue
import com.ibrahimcanerdogan.cryptoapp.ui.theme.Color1LightGreen
import com.ibrahimcanerdogan.cryptoapp.ui.theme.Color1Turquoise
import com.ibrahimcanerdogan.cryptoapp.ui.viewmodel.CryptoListViewModel
import com.ibrahimcanerdogan.cryptoapp.util.DateTimeUtils

@Composable
fun CryptoListScreen(
    navController: NavController,
    viewModel: CryptoListViewModel = hiltViewModel()
) {
    Scaffold { contentPadding ->
        Column(
           modifier = Modifier
               .fillMaxSize()
               .padding(contentPadding)
               .background(Color1DarkBlue)
        ) {
            Text(
                text = "Crypto Compose",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                style = TextStyle(
                    textAlign = TextAlign.Center,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            )

            Spacer(modifier = Modifier.height(10.dp))

            CryptoSearchBar(
                hint = "Search Crypto!",
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
                    .padding(horizontal = 10.dp),
                viewModel = viewModel
            ) {
                viewModel.searchCryptoDataList(it)
            }

            Spacer(modifier = Modifier.height(10.dp))

            CryptoListView(navController = navController, viewModel = viewModel)
        }
    }
}

@Composable
fun CryptoSearchBar(
    modifier: Modifier = Modifier,
    viewModel: CryptoListViewModel,
    hint: String = "",
    onSearch: (String) -> Unit = {}
) {
    var searchText by remember { mutableStateOf("") }
    var isHintDisplayed by remember { mutableStateOf(hint != "") }

    Box(modifier = modifier) {
        BasicTextField(
            value = searchText,
            onValueChange = {
                searchText = it
                if(it.length > 3 && it.isNotEmpty()) onSearch(it)
                if (it.isEmpty()) viewModel.loadAllCryptoMetaData()
            },
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(color = Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .background(Color.White)
                .border(1.dp, Color1Turquoise, RoundedCornerShape(20.dp))
                .padding(horizontal = 20.dp, vertical = 20.dp)
                .onFocusChanged {
                    isHintDisplayed = it.isFocused != true && searchText.isEmpty()
                }
        )
        if (isHintDisplayed) {
            Text(text = hint,
                modifier= Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterStart)
                    .padding(10.dp),
                color = Color.LightGray
            )
        }
    }
}

@Composable
fun CryptoListView(
    navController: NavController,
    viewModel: CryptoListViewModel
) {
    val cryptoList by remember { viewModel.cryptoMetaDataResponse }
    val errorMessage by remember { viewModel.onError }
    val isLoading by remember { viewModel.isLoading }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color1DarkBlue),
        contentAlignment = Alignment.Center
    ) {
       if (cryptoList.isNotEmpty()) {
           CryptoList(cryptoList = cryptoList, navController = navController)
       } else {
          if (isLoading) CircularProgressIndicator(color = Color1LightGreen)
          else if (errorMessage.isNotEmpty()) RetryView(error = errorMessage) { viewModel.loadAllCryptoMetaData() }
       }
    }
}

@Composable
fun CryptoList(
    cryptoList: List<CryptoMetaData>,
    navController: NavController
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top
    ) {
        items(cryptoList) {
            CryptoListRow(navController = navController, crypto = it)
        }
    }
}

@Composable
fun CryptoListRow(
    navController: NavController,
    crypto: CryptoMetaData
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 3.dp, horizontal = 15.dp)
            .clip(RoundedCornerShape(10.dp))
            .border(1.dp, Color.White, RoundedCornerShape(10.dp))
            .clickable {
                navController.navigate("crypto_detail_screen/${crypto.metaSymbol}")
            },
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color1LightGreen,
            contentColor = Color.Black
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(0.5f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = crypto.metaName,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(3.dp),
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimary
                )
                Text(
                    text = crypto.metaSymbol,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(3.dp),
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }

            Column(
                modifier = Modifier.fillMaxWidth(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = "${String.format("%.2f", crypto.metaQuote.quoteDollar.dollarPrice)}$",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(3.dp),
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimary
                )
                Text(
                    text = DateTimeUtils.convertDate(crypto.metaLastUpdated),
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.padding(3.dp),
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}

@Composable
fun RetryView(
    error: String,
    onRetry: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
       Text(
           text = error,
           color = Color.Red,
           fontSize = 20.sp
       )
        Spacer(modifier = Modifier.height(20.dp))

        IconButton(
            onClick = { onRetry() },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Icon(imageVector = Icons.Default.Autorenew,
                modifier = Modifier.size(50.dp),
                tint = Color.White,
                contentDescription = "Retry Icon"
            )
        }
    }
}