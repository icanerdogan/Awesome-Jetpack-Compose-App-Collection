package com.ibrahimcanerdogan.jetweather.data.repository

import com.ibrahimcanerdogan.jetweather.data.mapper.toForecastDaily
import com.ibrahimcanerdogan.jetweather.data.mapper.toWeatherInfo
import com.ibrahimcanerdogan.jetweather.data.remote.WeatherAPIService
import com.ibrahimcanerdogan.jetweather.data.remote.dto.daily.ForecastDailyDTO
import com.ibrahimcanerdogan.jetweather.domain.model.daily.ForecastDaily
import com.ibrahimcanerdogan.jetweather.domain.model.hourly.ForecastHourly
import com.ibrahimcanerdogan.jetweather.domain.repository.WeatherRepository
import com.ibrahimcanerdogan.jetweather.util.Resource
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val apiService: WeatherAPIService
): WeatherRepository {

    override suspend fun getHourlyForecast(lat: Double, long: Double): Resource<ForecastHourly> {
        val response = apiService.getWeatherForecastHourlyData(lat, long)
        return if(response.isSuccessful) {
            response.body()?.let {
                Resource.Success(it.toWeatherInfo())
            } ?: run {
                Resource.Error("Hourly Forecast Not Found!", null)
            }
        } else {
            Resource.Error(response.errorBody()?.toString() ?: "")
        }
    }

    override suspend fun getDailyForecast(lat: Double, long: Double): Resource<ForecastDaily> {
        val response = apiService.getWeatherForecastDailyData(lat, long)
        return if(response.isSuccessful) {
            response.body()?.let {
                Resource.Success(it.toForecastDaily())
            } ?: run {
                Resource.Error("Daily Forecast Not Found!", null)
            }
        } else {
            Resource.Error(response.errorBody()?.toString() ?: "")
        }
    }
}