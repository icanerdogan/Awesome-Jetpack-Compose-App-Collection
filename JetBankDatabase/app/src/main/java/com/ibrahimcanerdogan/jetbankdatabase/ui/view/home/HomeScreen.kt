package com.ibrahimcanerdogan.jetbankdatabase.ui.view.home

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import androidx.activity.compose.BackHandler
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.google.gson.Gson
import com.ibrahimcanerdogan.jetbankdatabase.R
import com.ibrahimcanerdogan.jetbankdatabase.data.model.BankDataItem
import com.ibrahimcanerdogan.jetbankdatabase.ui.component.ErrorImage
import com.ibrahimcanerdogan.jetbankdatabase.ui.component.LoadingAnimation
import com.ibrahimcanerdogan.jetbankdatabase.ui.component.NoDataImage
import com.ibrahimcanerdogan.jetbankdatabase.ui.navigation.AppScreen
import com.ibrahimcanerdogan.jetbankdatabase.ui.viewmodel.HomeViewModel
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import java.util.Locale

@Composable
fun HomeScreen(navController: NavController) {
    val viewModel: HomeViewModel = hiltViewModel()
    val context = LocalContext.current
    val gson = Gson()

    BackHandler {
        (context as? Activity)?.finish()
    }

    val state = viewModel.homeState.value
    val filteredBankData = viewModel.filteredBankList.value
    val searchQuery = remember { mutableStateOf("") }

    val currentNavController = rememberUpdatedState(navController)
    val flagTR = painterResource(R.drawable.flag_tr)
    val flagUS = painterResource(R.drawable.flag_en)

    Scaffold(
        topBar = {
            Column {
                TextField(
                    value = searchQuery.value,
                    onValueChange = { newValue ->
                       searchQuery.value = newValue
                       viewModel.filterBankList(searchQuery.value)
                    },
                    label = {
                        Text("Search City/District")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp, horizontal = 10.dp)
                )
            }
        }
    ) { paddingValues ->
        Column (modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)) {
            // Locale Decision
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                AsyncImage(
                    model = R.drawable.flag_tr,
                    modifier = Modifier
                        .size(width = 100.dp, height = 80.dp)
                        .clickable {
                            changeLanguage(context, currentNavController, "tr")
                        },
                    contentScale = ContentScale.Crop,
                    contentDescription = "Turkey Flag"
                )
                AsyncImage(
                    model = R.drawable.flag_en,
                    modifier = Modifier
                        .size(width = 100.dp, height = 80.dp)
                        .clickable {
                            changeLanguage(context, currentNavController, "en")
                        },
                    contentScale = ContentScale.Crop,
                    contentDescription = "US Flag"
                )
            }

            // State Management
            if(state.isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    LoadingAnimation()
                }
            } else {
                if (!state.errorMessage.isNullOrEmpty()) {
                    ErrorImage()
                }
            }
            // Lazy Column
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
               if (filteredBankData.isEmpty()) {
                   item {
                       Box(
                           modifier = Modifier.fillMaxSize(),
                           contentAlignment = Alignment.Center
                       ) {
                           Column(
                               modifier = Modifier.fillMaxSize(),
                               verticalArrangement = Arrangement.Center,
                               horizontalAlignment = Alignment.CenterHorizontally
                           ) {
                               if(!state.isLoading) NoDataImage()
                           }
                       }
                   }
               } else {
                   items(filteredBankData) { bankItem ->
                       BankDataCard(bankItem) {
                           val bankDataJson = gson.toJson(bankItem)
                           val encodedBankDataJson = URLEncoder.encode(bankDataJson, StandardCharsets.UTF_8.toString())
                           navController.navigate("${AppScreen.DETAIL_SCREEN.name}/$encodedBankDataJson")
                       }
                   }
               }
            }
        }
    }
}

@Preview
@Composable
fun BankDataCard(
    bankItem: BankDataItem = BankDataItem(),
    onClickCard: () -> Unit = {}
) {
    Card(
        modifier = Modifier
            .padding(vertical = 10.dp, horizontal = 20.dp)
            .clickable { onClickCard.invoke() }
    ) {
        Column(
            modifier = Modifier.padding(15.dp)
        ) {
            if (bankItem.bankBranch.isNullOrEmpty()) {
                BankDataCardItem(Icons.Default.AccountBalance, "${bankItem.bankDistrict} / ${bankItem.bankCity}")
            } else {
                BankDataCardItem(Icons.Default.AccountBalance, "${bankItem.bankBranch}")
            }

            Spacer(Modifier.height(20.dp))

            Text(
                text = "${bankItem.bankAddress}",
                style = TextStyle(
                    color = MaterialTheme.colorScheme.secondary,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Normal,
                    fontStyle = FontStyle.Italic
                )
            )
        }
    }
}

@Composable
fun BankDataCardItem(
    bankItemVector: ImageVector,
    bankItemText: String?
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = bankItemVector,
            modifier = Modifier.size(30.dp),
            tint = MaterialTheme.colorScheme.onPrimaryContainer,
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = "$bankItemText",
            style = TextStyle(
                color = MaterialTheme.colorScheme.secondary,
                fontSize = 17.sp,
                fontWeight = FontWeight.Medium
            )
        )
    }
    Spacer(Modifier.height(5.dp))
}

private fun changeLanguage(
    context: Context,
    currentNavController: State<NavController>,
    language: String
) {
    val locale = Locale(language)
    Locale.setDefault(locale)
    val config = Configuration()
    config.setLocale(locale)
    context.resources.updateConfiguration(config, context.resources.displayMetrics)

    val currentDestination = currentNavController.value.currentDestination?.id
    currentNavController.value.popBackStack()
    currentDestination?.let {
        currentNavController.value.navigate(it)
    }
}