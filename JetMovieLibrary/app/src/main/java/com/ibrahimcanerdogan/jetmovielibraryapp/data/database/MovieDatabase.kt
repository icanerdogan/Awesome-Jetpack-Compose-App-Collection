package com.ibrahimcanerdogan.jetmovielibraryapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ibrahimcanerdogan.jetmovielibraryapp.data.database.favorite.MovieFavoriteDAO
import com.ibrahimcanerdogan.jetmovielibraryapp.data.database.favorite.MovieFavoriteEntity

@Database(entities = [MovieFavoriteEntity::class], version = 3)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieFavoriteDao(): MovieFavoriteDAO
}