package com.ibrahimcanerdogan.jetmarvelcomicslibrary.dependencyinjection

import com.ibrahimcanerdogan.jetmarvelcomicslibrary.data.database.dao.CharacterDao
import com.ibrahimcanerdogan.jetmarvelcomicslibrary.data.database.dao.NoteDao
import com.ibrahimcanerdogan.jetmarvelcomicslibrary.data.network.MarvelAPIService
import com.ibrahimcanerdogan.jetmarvelcomicslibrary.data.repository.DatabaseRepositoryImpl
import com.ibrahimcanerdogan.jetmarvelcomicslibrary.data.repository.NetworkRepositoryImpl
import com.ibrahimcanerdogan.jetmarvelcomicslibrary.domain.repository.DatabaseRepository
import com.ibrahimcanerdogan.jetmarvelcomicslibrary.domain.repository.NetworkRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideDatabaseRepository(characterDao: CharacterDao, noteDao: NoteDao): DatabaseRepository =
        DatabaseRepositoryImpl(characterDao, noteDao)

    @Provides
    @Singleton
    fun provideNetworkRepository(marvelAPIService: MarvelAPIService): NetworkRepository =
        NetworkRepositoryImpl(marvelAPIService)

}