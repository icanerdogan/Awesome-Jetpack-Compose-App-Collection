package com.ibrahimcanerdogan.jetmovielibraryapp.ui.dependencyinjection

import com.ibrahimcanerdogan.jetmovielibraryapp.domain.repository.MovieRepository
import com.ibrahimcanerdogan.jetmovielibraryapp.domain.usecase.GetAllSearchedMoviesUseCase
import com.ibrahimcanerdogan.jetmovielibraryapp.domain.usecase.GetMovieDetailDataUseCase
import com.ibrahimcanerdogan.jetmovielibraryapp.domain.usecase.GetMovieFavoriteDataLocalUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideMovieDetailDataUseCase(movieRepository: MovieRepository): GetMovieDetailDataUseCase {
        return GetMovieDetailDataUseCase(movieRepository)
    }

    @Provides
    @Singleton
    fun provideAllSearchedMoviesUseCase(movieRepository: MovieRepository): GetAllSearchedMoviesUseCase {
        return GetAllSearchedMoviesUseCase(movieRepository)
    }

    @Provides
    @Singleton
    fun provideMovieFavoriteDataLocalUseCase(movieRepository: MovieRepository): GetMovieFavoriteDataLocalUseCase {
        return GetMovieFavoriteDataLocalUseCase(movieRepository)
    }
}