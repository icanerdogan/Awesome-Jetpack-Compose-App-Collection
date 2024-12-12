package com.ibrahimcanerdogan.jetbankdatabase.dependencyinjection

import com.ibrahimcanerdogan.jetbankdatabase.data.repository.BankRepositoryImpl
import com.ibrahimcanerdogan.jetbankdatabase.data.repository.datasource.RemoteDataSource
import com.ibrahimcanerdogan.jetbankdatabase.domain.repository.BankRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideBankRepository(remoteDataSource: RemoteDataSource) : BankRepository {
        return BankRepositoryImpl(remoteDataSource)
    }

}