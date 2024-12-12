package com.ibrahimcanerdogan.jetweather.ui.screen.weather.state

import com.ibrahimcanerdogan.jetweather.domain.model.daily.ForecastDaily
import com.ibrahimcanerdogan.jetweather.domain.model.hourly.ForecastHourly

data class WeatherUIState(
    val forecastHourly: ForecastHourly? = null,
    val forecastDaily: ForecastDaily? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)