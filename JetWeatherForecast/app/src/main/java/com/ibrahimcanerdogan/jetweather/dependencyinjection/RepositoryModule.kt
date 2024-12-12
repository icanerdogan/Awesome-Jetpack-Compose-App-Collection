package com.ibrahimcanerdogan.jetweather.dependencyinjection

import com.ibrahimcanerdogan.jetweather.data.repository.WeatherRepositoryImpl
import com.ibrahimcanerdogan.jetweather.domain.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindWeatherRepository(weatherRepositoryImpl: WeatherRepositoryImpl): WeatherRepository

}