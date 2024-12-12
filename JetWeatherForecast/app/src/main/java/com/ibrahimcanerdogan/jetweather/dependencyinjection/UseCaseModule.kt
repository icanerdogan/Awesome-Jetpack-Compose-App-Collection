package com.ibrahimcanerdogan.jetweather.dependencyinjection

import com.ibrahimcanerdogan.jetweather.domain.repository.WeatherRepository
import com.ibrahimcanerdogan.jetweather.domain.usecase.GetDailyForecastUseCase
import com.ibrahimcanerdogan.jetweather.domain.usecase.GetHourlyForecastUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideGetHourlyForecastUseCase(weatherRepository: WeatherRepository) : GetHourlyForecastUseCase =
        GetHourlyForecastUseCase(weatherRepository)

    @Provides
    fun provideGetDailyForecastUseCase(weatherRepository: WeatherRepository) : GetDailyForecastUseCase =
        GetDailyForecastUseCase(weatherRepository)
}