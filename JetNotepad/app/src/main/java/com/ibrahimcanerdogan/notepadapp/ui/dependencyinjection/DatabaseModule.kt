package com.ibrahimcanerdogan.notepadapp.ui.dependencyinjection

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ibrahimcanerdogan.notepadapp.data.database.NoteDatabase
import com.ibrahimcanerdogan.notepadapp.data.database.NoteDatabaseDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Singleton
    @Provides
    fun provideRoomDatabase(
        @ApplicationContext context: Context
    ) : NoteDatabase {
        return Room.databaseBuilder(context, NoteDatabase::class.java, "notes_db")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @Singleton
    @Provides
    fun provideNoteDao(noteDatabase: NoteDatabase) : NoteDatabaseDAO {
        return noteDatabase.noteDao()
    }
}