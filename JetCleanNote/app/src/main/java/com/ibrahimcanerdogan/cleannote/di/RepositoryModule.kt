package com.ibrahimcanerdogan.cleannote.di

import com.ibrahimcanerdogan.cleannote.data.database.NoteDatabase
import com.ibrahimcanerdogan.cleannote.data.repository.NoteRepositoryImpl
import com.ibrahimcanerdogan.cleannote.domain.repository.NoteRepository
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
    fun provideNoteRepository(noteDatabase: NoteDatabase): NoteRepository {
        return NoteRepositoryImpl(noteDatabase.noteDao)
    }
}