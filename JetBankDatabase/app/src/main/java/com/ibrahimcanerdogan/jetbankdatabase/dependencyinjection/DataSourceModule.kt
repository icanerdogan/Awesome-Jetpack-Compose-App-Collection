package com.ibrahimcanerdogan.jetbankdatabase.dependencyinjection

import com.ibrahimcanerdogan.jetbankdatabase.data.network.BankAPIService
import com.ibrahimcanerdogan.jetbankdatabase.data.repository.datasource.RemoteDataSource
import com.ibrahimcanerdogan.jetbankdatabase.data.repository.datasourceImpl.RemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideRemoteDataSource(bankAPIService: BankAPIService) : RemoteDataSource {
        return RemoteDataSourceImpl(bankAPIService)
    }

}