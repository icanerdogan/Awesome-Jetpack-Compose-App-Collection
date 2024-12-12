package com.ibrahimcanerdogan.jetmovielibraryapp.data.database.favorite

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MovieFavoriteDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoriteMovieEntityDatabase(movieEntity: MovieFavoriteEntity)

    @Delete
    fun deleteFavoriteMovieEntityDatabase(movieEntity: MovieFavoriteEntity)

    @Query("SELECT * FROM movie_favorite_table")
    fun getAllFavoriteMovieEntityFromDatabase(): List<MovieFavoriteEntity>
}