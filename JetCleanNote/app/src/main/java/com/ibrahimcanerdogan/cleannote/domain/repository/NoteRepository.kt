package com.ibrahimcanerdogan.cleannote.domain.repository

import com.ibrahimcanerdogan.cleannote.data.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    fun getAllNoteRepository(): Flow<List<Note>>

    suspend fun getSingleNoteRepository(id: Int): Note?

    suspend fun insertNoteRepository(note: Note)

    suspend fun deleteNoteRepository(note: Note)
}