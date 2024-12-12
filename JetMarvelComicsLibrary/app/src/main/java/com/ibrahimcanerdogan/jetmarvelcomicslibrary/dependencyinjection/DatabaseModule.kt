package com.ibrahimcanerdogan.jetmarvelcomicslibrary.dependencyinjection

import android.content.Context
import androidx.room.Room
import com.ibrahimcanerdogan.jetmarvelcomicslibrary.data.database.MarvelDatabase
import com.ibrahimcanerdogan.jetmarvelcomicslibrary.data.database.dao.CharacterDao
import com.ibrahimcanerdogan.jetmarvelcomicslibrary.data.database.dao.NoteDao
import com.ibrahimcanerdogan.jetmarvelcomicslibrary.utils.Constants.DB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideMarvelDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, MarvelDatabase::class.java, DB).build()

    @Provides
    @Singleton
    fun provideCharacterDao(marvelDatabase: MarvelDatabase): CharacterDao {
        return marvelDatabase.characterDao()
    }

    @Provides
    @Singleton
    fun provideNoteDao(marvelDatabase: MarvelDatabase): NoteDao {
        return marvelDatabase.noteDao()
    }
}