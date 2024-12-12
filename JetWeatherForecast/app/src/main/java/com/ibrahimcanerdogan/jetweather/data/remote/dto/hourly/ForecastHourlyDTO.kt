package com.ibrahimcanerdogan.jetweather.data.remote.dto.hourly

import com.squareup.moshi.Json

data class ForecastHourlyDTO(
    @field:Json(name = "hourly")
    val weatherData: ForecastHourlyDataDTO
)