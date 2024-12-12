package com.ibrahimcanerdogan.jetmovielibraryapp.ui.dependencyinjection

import com.ibrahimcanerdogan.jetmovielibraryapp.data.database.favorite.MovieFavoriteDAO
import com.ibrahimcanerdogan.jetmovielibraryapp.data.network.MovieAPIService
import com.ibrahimcanerdogan.jetmovielibraryapp.data.repository.datasource.LocalDataSource
import com.ibrahimcanerdogan.jetmovielibraryapp.data.repository.datasource.RemoteDataSource
import com.ibrahimcanerdogan.jetmovielibraryapp.data.repository.datasourceImpl.LocalDataSourceImpl
import com.ibrahimcanerdogan.jetmovielibraryapp.data.repository.datasourceImpl.RemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Singleton
    @Provides
    fun provideRemoteDataSource(
        apiService: MovieAPIService
    ) : RemoteDataSource {
        return RemoteDataSourceImpl(apiService)
    }

    @Singleton
    @Provides
    fun provideLocalDataSource(
        movieFavoriteDAO: MovieFavoriteDAO
    ) : LocalDataSource {
        return LocalDataSourceImpl(movieFavoriteDAO)
    }

}