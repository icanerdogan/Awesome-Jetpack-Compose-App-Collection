package com.ibrahimcanerdogan.jetweather.data.remote.dto.daily

import com.squareup.moshi.Json

data class ForecastDailyDTO (
    @field:Json(name = "daily")
    val forecastDailyDataDto: ForecastDailyDataDTO
)