package com.ibrahimcanerdogan.jetweather.data.remote

import com.ibrahimcanerdogan.jetweather.data.remote.dto.daily.ForecastDailyDTO
import com.ibrahimcanerdogan.jetweather.data.remote.dto.hourly.ForecastHourlyDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPIService {

    @GET("v1/forecast?hourly=temperature_2m,weathercode,relativehumidity_2m,windspeed_10m,pressure_msl")
    suspend fun getWeatherForecastHourlyData(
        @Query("latitude") lat: Double,
        @Query("longitude") long: Double
    ): Response<ForecastHourlyDTO>

    @GET("v1/forecast?daily=weather_code,temperature_2m_max,temperature_2m_min,sunrise,sunset,rain_sum,showers_sum,snowfall_sum")
    suspend fun getWeatherForecastDailyData(
        @Query("latitude") lat: Double,
        @Query("longitude") long: Double
    ) : Response<ForecastDailyDTO>
}