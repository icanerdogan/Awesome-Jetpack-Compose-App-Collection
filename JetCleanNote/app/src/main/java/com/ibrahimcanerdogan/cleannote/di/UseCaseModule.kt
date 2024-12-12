package com.ibrahimcanerdogan.cleannote.di

import com.ibrahimcanerdogan.cleannote.domain.repository.NoteRepository
import com.ibrahimcanerdogan.cleannote.domain.usecase.AddNote
import com.ibrahimcanerdogan.cleannote.domain.usecase.DeleteNote
import com.ibrahimcanerdogan.cleannote.domain.usecase.GetNote
import com.ibrahimcanerdogan.cleannote.domain.usecase.GetNotes
import com.ibrahimcanerdogan.cleannote.domain.usecase.NoteUseCase
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
    fun provideNoteUseCases(repository: NoteRepository): NoteUseCase {
        return NoteUseCase(
            getNotes = GetNotes(repository),
            deleteNote = DeleteNote(repository),
            addNote = AddNote(repository),
            getNote = GetNote(repository)
        )
    }
}