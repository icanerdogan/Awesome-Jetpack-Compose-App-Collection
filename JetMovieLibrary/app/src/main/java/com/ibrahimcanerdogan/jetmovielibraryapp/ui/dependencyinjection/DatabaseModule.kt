package com.ibrahimcanerdogan.jetmovielibraryapp.ui.dependencyinjection

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.ibrahimcanerdogan.jetmovielibraryapp.data.database.MovieDatabase
import com.ibrahimcanerdogan.jetmovielibraryapp.data.database.favorite.MovieFavoriteDAO
import com.ibrahimcanerdogan.jetmovielibraryapp.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideMovieDatabase(application: Application): MovieDatabase {
        return Room
            .databaseBuilder(
                application,
                MovieDatabase::class.java,
                Constants.DATABASE_NAME
            )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideMovieFavoriteDao(movieDatabase: MovieDatabase): MovieFavoriteDAO {
        return movieDatabase.movieFavoriteDao()
    }

}