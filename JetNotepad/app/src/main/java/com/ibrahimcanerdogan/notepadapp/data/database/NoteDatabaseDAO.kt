package com.ibrahimcanerdogan.notepadapp.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDatabaseDAO {

    @Query("SELECT * FROM notes_table")
    fun getAllNotesFromDatabase() : Flow<List<NoteEntity>>

    @Query("SELECT * FROM notes_table WHERE note_id=:selectedNoteID")
    suspend fun getNoteFromDatabase(selectedNoteID: String): NoteEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNoteToDatabase(newNote: NoteEntity)

    @Query("SELECT * FROM notes_table WHERE note_title LIKE '%' || :noteSearchText || '%' OR note_subtitle LIKE '%' || :noteSearchText || '%'")
    suspend fun searchNoteToDatabase(noteSearchText: String): List<NoteEntity>

    @Update
    suspend fun updateNoteToDatabase(noteEntity: NoteEntity)

    @Query("DELETE FROM notes_table")
    suspend fun deleteAllNotesFromDatabase()

    @Delete
    suspend fun deleteNote(deleteNote: NoteEntity)
}