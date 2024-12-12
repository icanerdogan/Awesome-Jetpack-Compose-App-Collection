package com.ibrahimcanerdogan.jetweather.dependencyinjection

import com.ibrahimcanerdogan.jetweather.data.location.DefaultLocationTracker
import com.ibrahimcanerdogan.jetweather.domain.location.LocationTracker
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LocationTrackerModule {

    @Binds
    @Singleton
    abstract fun bindLocationTracker(defaultLocationTracker: DefaultLocationTracker): LocationTracker

}