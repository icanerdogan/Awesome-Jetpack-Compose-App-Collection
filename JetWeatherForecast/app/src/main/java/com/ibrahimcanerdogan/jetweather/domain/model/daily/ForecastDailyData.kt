package com.ibrahimcanerdogan.jetweather.domain.model.daily

import java.time.LocalDate
import java.time.LocalDateTime

data class ForecastDailyData(
    val date: LocalDate,
    val maxTemperature: Double,
    val minTemperature: Double,
    val weatherCode: Int,
    val sunrise: LocalDateTime,
    val sunset: LocalDateTime,
    val rainSum: Double,
    val showersSum: Double,
    val snowfallSum: Double
)