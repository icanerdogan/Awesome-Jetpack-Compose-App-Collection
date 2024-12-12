package com.ibrahimcanerdogan.cryptoapp.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import coil.transform.CircleCropTransformation
import com.ibrahimcanerdogan.cryptoapp.data.model.detail.CryptoDetailResponse
import com.ibrahimcanerdogan.cryptoapp.data.model.detail.parseData
import com.ibrahimcanerdogan.cryptoapp.ui.theme.Color1DarkBlue
import com.ibrahimcanerdogan.cryptoapp.ui.theme.Color1LightGreen
import com.ibrahimcanerdogan.cryptoapp.ui.viewmodel.CryptoDetailViewModel
import com.ibrahimcanerdogan.cryptoapp.util.DateTimeUtils
import com.ibrahimcanerdogan.cryptoapp.util.Resource

@Composable
fun CryptoDetailScreen(
    cryptoID: String,
    viewModel: CryptoDetailViewModel = hiltViewModel()
) {

    val cryptoItem = produceState<Resource<CryptoDetailResponse>>(initialValue = Resource.Loading()) {
        value = viewModel.loadCryptoDetailData(cryptoID)
    }.value

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Color1DarkBlue)
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when(cryptoItem) {
                is Resource.Success -> {
                    val selectedCrypto = cryptoItem.data!!.parseData(cryptoID)
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = selectedCrypto.detailName!!,
                            style = TextStyle(
                                fontSize = 40.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                textAlign = TextAlign.Center
                            )
                        )
                        Text(
                            text = selectedCrypto.detailSymbol!!,
                            style = TextStyle(
                                fontSize = 30.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                textAlign = TextAlign.Center
                            )
                        )
                        Text(
                            text = DateTimeUtils.convertDate(selectedCrypto.detailDateAdded!!),
                            style = TextStyle(
                                fontSize = 30.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                textAlign = TextAlign.Center
                            )
                        )
                    }

                    val painter = rememberAsyncImagePainter(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(selectedCrypto.detailLogo)
                            .transformations(CircleCropTransformation())
                            .size(Size.ORIGINAL)
                            .crossfade(true)
                            .build()
                    )
                    Image(
                        painter = painter,
                        modifier = Modifier
                            .size(200.dp, 200.dp)
                            .clip(CircleShape),
                        contentDescription = "Detail Crypto Image"
                    )

                    Text(
                        text = selectedCrypto.detailDescription!!,
                        modifier = Modifier.padding(10.dp),
                        style = TextStyle(
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.White,
                            textAlign = TextAlign.Center
                        )
                    )
                }
                is Resource.Error -> {
                    Text(
                        text = cryptoItem.message ?: "Something went wrong!",
                        color = Color.Red,
                        fontSize = 20.sp
                    )
                }
                is Resource.Loading -> {
                    CircularProgressIndicator(color = Color1LightGreen)
                }
            }
        }
    }
}