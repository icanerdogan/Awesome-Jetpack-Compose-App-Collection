package com.ibrahimcanerdogan.jetweather.domain.model.hourly

import java.time.LocalDateTime

data class ForecastHourlyData(
    val time: LocalDateTime,
    val temperatureCelsius: Double,
    val pressure: Double,
    val windSpeed: Double,
    val humidity: Double,
    val forecastHourlyType: ForecastHourlyType
)