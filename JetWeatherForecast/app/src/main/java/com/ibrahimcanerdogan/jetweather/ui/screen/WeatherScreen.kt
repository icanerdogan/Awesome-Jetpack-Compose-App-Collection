package com.ibrahimcanerdogan.jetweather.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ibrahimcanerdogan.jetweather.ui.component.WeatherCard
import com.ibrahimcanerdogan.jetweather.ui.component.WeatherHourlyForecast
import com.ibrahimcanerdogan.jetweather.ui.screen.weather.WeatherViewModel

@Composable
fun WeatherScreen(
    modifier: Modifier = Modifier,
    viewModel: WeatherViewModel
) {
    val state = viewModel.state

    Box(modifier = modifier) {
        // Weather Content
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
        ) {
            WeatherCard(state = state)
            Spacer(modifier = Modifier.height(16.dp))
            WeatherHourlyForecast(state = state)
            // TODO: WEATHER DAILY FORECAST.
        }

        // Loading Indicator
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }

        // Error Message and Retry Button
        state.error?.let { error ->
            ErrorContent(
                error = error,
                onRetry = { viewModel.loadHourlyForecastInfo() },
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Composable
fun ErrorContent(
    error: String,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = error,
            color = Color.Red,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Button(onClick = onRetry) {
            Text(
                text = "Try Again",
                color = Color.White,
                style = MaterialTheme.typography.titleSmall
            )
        }
    }
}
