package com.ibrahimcanerdogan.jetweather.domain.usecase

import com.ibrahimcanerdogan.jetweather.domain.model.hourly.ForecastHourly
import com.ibrahimcanerdogan.jetweather.domain.repository.WeatherRepository
import com.ibrahimcanerdogan.jetweather.util.DefaultRetryPolicy
import com.ibrahimcanerdogan.jetweather.util.Resource
import com.ibrahimcanerdogan.jetweather.util.checkError
import com.ibrahimcanerdogan.jetweather.util.retryWithPolicy
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class GetHourlyForecastUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) {

    operator fun invoke(lat : Double, long : Double): Flow<Resource<ForecastHourly>> = flow {
        emit(weatherRepository.getHourlyForecast(lat, long))
    }.retryWithPolicy(DefaultRetryPolicy())
        .catch { emit(checkError(it)) }
        .onStart { emit(Resource.Loading()) }

}