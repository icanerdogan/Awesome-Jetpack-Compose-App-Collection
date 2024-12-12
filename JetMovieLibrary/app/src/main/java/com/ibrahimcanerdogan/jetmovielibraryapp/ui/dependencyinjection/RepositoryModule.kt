package com.ibrahimcanerdogan.jetmovielibraryapp.ui.dependencyinjection

import com.ibrahimcanerdogan.jetmovielibraryapp.data.repository.MovieRepositoryImpl
import com.ibrahimcanerdogan.jetmovielibraryapp.data.repository.datasource.LocalDataSource
import com.ibrahimcanerdogan.jetmovielibraryapp.data.repository.datasource.RemoteDataSource
import com.ibrahimcanerdogan.jetmovielibraryapp.domain.repository.MovieRepository
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
    fun provideMovieRepository(
        localDataSource: LocalDataSource,
        remoteDataSource: RemoteDataSource
    ): MovieRepository {
        return MovieRepositoryImpl(localDataSource, remoteDataSource)
    }
}