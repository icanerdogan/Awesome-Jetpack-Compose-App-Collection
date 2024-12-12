package com.ibrahimcanerdogan.stockmarket.ui.screen.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ibrahimcanerdogan.stockmarket.ui.component.StockChart
import com.ramcosta.composedestinations.annotation.Destination

@Composable
@Destination
fun CompanyInfoScreen(
    symbol: String,
    viewModel: CompanyDetailViewModel = hiltViewModel()
) {
    val state = viewModel.state

    Scaffold { paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            if (state.detailError == null) {

                Column {
                    state.detailCompany?.let { company ->
                        Text(
                            text = company.companyDetailName,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = symbol, //company.companyDetailSymbol,
                            fontStyle = FontStyle.Italic,
                            fontSize = 14.sp,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        HorizontalDivider(modifier = Modifier.fillMaxWidth())
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Industry: ${company.companyDetailIndustry}",
                            fontSize = 14.sp,
                            modifier = Modifier.fillMaxWidth(),
                            overflow = TextOverflow.Ellipsis
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Country: ${company.companyDetailCountry}",
                            fontSize = 14.sp,
                            modifier = Modifier.fillMaxWidth(),
                            overflow = TextOverflow.Ellipsis
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        HorizontalDivider(modifier = Modifier.fillMaxWidth())
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = company.companyDetailDescription,
                            fontSize = 12.sp,
                            modifier = Modifier.fillMaxWidth(),
                        )
                        if (state.detailIntradayInfo.isNotEmpty()) {
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(text = "Market Summary")
                            Spacer(modifier = Modifier.height(32.dp))
                            StockChart(
                                infos = state.detailIntradayInfo,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(250.dp)
                                    .align(CenterHorizontally)
                            )
                        }
                    }
                }
            }

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Center
            ) {
                if (state.detailIsLoading) {
                    CircularProgressIndicator()
                } else if (state.detailError != null) {
                    Text(
                        text = state.detailError,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
        }
    }
}
