package com.ibrahimcanerdogan.notepadapp.data.repository

import com.ibrahimcanerdogan.notepadapp.data.database.NoteDatabaseDAO
import com.ibrahimcanerdogan.notepadapp.data.database.NoteEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NoteRepository @Inject constructor(
    private val noteDatabaseDAO: NoteDatabaseDAO
) {

    fun getAllNotesDatabase() = noteDatabaseDAO.getAllNotesFromDatabase()
        .flowOn(Dispatchers.IO)
        .conflate()

    suspend fun addNoteDatabase(addNote: NoteEntity) {
        noteDatabaseDAO.insertNoteToDatabase(addNote)
    }

    suspend fun searchNoteDatabase(noteSearchText: String) : List<NoteEntity> {
        return noteDatabaseDAO.searchNoteToDatabase(noteSearchText)
    }

    suspend fun updateNoteDatabase(updateNote: NoteEntity) {
        noteDatabaseDAO.updateNoteToDatabase(updateNote)
    }

    suspend fun deleteNoteDatabase(deleteNote: NoteEntity) {
        noteDatabaseDAO.deleteNote(deleteNote)
    }

    suspend fun deleteAllNotesDatabase() {
        noteDatabaseDAO.deleteAllNotesFromDatabase()
    }
}