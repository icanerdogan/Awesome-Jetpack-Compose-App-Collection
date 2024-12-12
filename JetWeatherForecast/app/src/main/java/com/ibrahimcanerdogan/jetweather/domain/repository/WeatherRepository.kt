package com.ibrahimcanerdogan.jetweather.domain.repository

import com.ibrahimcanerdogan.jetweather.domain.model.daily.ForecastDaily
import com.ibrahimcanerdogan.jetweather.domain.model.hourly.ForecastHourly
import com.ibrahimcanerdogan.jetweather.util.Resource

interface WeatherRepository {
    suspend fun getHourlyForecast(lat: Double, long: Double): Resource<ForecastHourly>
    suspend fun getDailyForecast(lat: Double, long: Double): Resource<ForecastDaily>
}