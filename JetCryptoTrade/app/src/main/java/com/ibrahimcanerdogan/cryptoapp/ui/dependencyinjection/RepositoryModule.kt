package com.ibrahimcanerdogan.cryptoapp.ui.dependencyinjection

import com.ibrahimcanerdogan.cryptoapp.data.network.APIService
import com.ibrahimcanerdogan.cryptoapp.data.repository.DetailRepositoryImpl
import com.ibrahimcanerdogan.cryptoapp.data.repository.HomeRepositoryImpl
import com.ibrahimcanerdogan.cryptoapp.domain.repository.DetailRepository
import com.ibrahimcanerdogan.cryptoapp.domain.repository.HomeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideHomeRepository(
        apiService: APIService
    ) : HomeRepository {
        return HomeRepositoryImpl(apiService)
    }

    @Singleton
    @Provides
    fun provideDetailRepository(
        apiService: APIService
    ) : DetailRepository {
        return DetailRepositoryImpl(apiService)
    }
}