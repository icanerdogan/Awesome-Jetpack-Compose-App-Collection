package com.ibrahimcanerdogan.jetweather.data.mapper

import com.ibrahimcanerdogan.jetweather.data.remote.dto.daily.ForecastDailyDTO
import com.ibrahimcanerdogan.jetweather.data.remote.dto.daily.ForecastDailyDataDTO
import com.ibrahimcanerdogan.jetweather.data.remote.dto.hourly.ForecastHourlyDTO
import com.ibrahimcanerdogan.jetweather.data.remote.dto.hourly.ForecastHourlyDataDTO
import com.ibrahimcanerdogan.jetweather.domain.model.daily.ForecastDaily
import com.ibrahimcanerdogan.jetweather.domain.model.daily.ForecastDailyData
import com.ibrahimcanerdogan.jetweather.domain.model.hourly.ForecastHourly
import com.ibrahimcanerdogan.jetweather.domain.model.hourly.ForecastHourlyData
import com.ibrahimcanerdogan.jetweather.domain.model.hourly.ForecastHourlyType
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private data class IndexedWeatherData(
    val index: Int,
    val data: ForecastHourlyData
)

fun ForecastHourlyDataDTO.toWeatherDataMap(): Map<Int, List<ForecastHourlyData>> {
    return time.mapIndexed { index, time ->
        val temperature = temperatures[index]
        val weatherCode = weatherCodes[index]
        val windSpeed = windSpeeds[index]
        val pressure = pressures[index]
        val humidity = humidities[index]
        IndexedWeatherData(
            index = index,
            data = ForecastHourlyData(
                time = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME),
                temperatureCelsius = temperature,
                pressure = pressure,
                windSpeed = windSpeed,
                humidity = humidity,
                forecastHourlyType = ForecastHourlyType.fromWMO(weatherCode)
            )
        )
    }.groupBy {
        it.index / 24
    }.mapValues {
        it.value.map { it.data }
    }
}

fun ForecastHourlyDTO.toWeatherInfo(): ForecastHourly {
    val weatherDataMap = weatherData.toWeatherDataMap()
    val now = LocalDateTime.now()
    val currentWeatherData = weatherDataMap[0]?.find {
        val hour = if(now.minute < 30) now.hour else now.hour + 1
        it.time.hour == hour
    }
    return ForecastHourly(
        weatherDataPerDay = weatherDataMap,
        currentWeatherData = currentWeatherData
    )
}

fun ForecastDailyDataDTO.toForecastDailyDataList(): List<ForecastDailyData> {
    return time.mapIndexed { index, date ->
        ForecastDailyData(
            date = LocalDate.parse(date, DateTimeFormatter.ISO_DATE),
            maxTemperature = maxTemperatures[index],
            minTemperature = minTemperatures[index],
            weatherCode = weatherCodes[index],
            sunrise = LocalDateTime.parse(sunrise[index], DateTimeFormatter.ISO_DATE_TIME),
            sunset = LocalDateTime.parse(sunset[index], DateTimeFormatter.ISO_DATE_TIME),
            rainSum = rainSum.getOrElse(index) { 0.0 },
            showersSum = showersSum.getOrElse(index) { 0.0 },
            snowfallSum = snowfallSum.getOrElse(index) { 0.0 }
        )
    }
}

fun ForecastDailyDTO.toForecastDaily(): ForecastDaily {
    return ForecastDaily(
        dailyForecasts = forecastDailyDataDto.toForecastDailyDataList()
    )
}