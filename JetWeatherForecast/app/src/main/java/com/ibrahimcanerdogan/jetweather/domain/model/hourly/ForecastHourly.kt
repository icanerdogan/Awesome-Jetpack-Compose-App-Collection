package com.ibrahimcanerdogan.jetweather.domain.model.hourly

data class ForecastHourly(
    val weatherDataPerDay: Map<Int, List<ForecastHourlyData>>,
    val currentWeatherData: ForecastHourlyData?
)