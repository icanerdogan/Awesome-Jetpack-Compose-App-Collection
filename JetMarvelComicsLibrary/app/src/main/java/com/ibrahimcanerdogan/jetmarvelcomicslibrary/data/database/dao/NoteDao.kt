package com.ibrahimcanerdogan.jetmarvelcomicslibrary.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.ibrahimcanerdogan.jetmarvelcomicslibrary.data.database.model.NoteDBModel
import com.ibrahimcanerdogan.jetmarvelcomicslibrary.utils.Constants
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Query("SELECT * FROM ${Constants.NOTE_TABLE} ORDER BY modelId")
    fun getAllNotesDatabase(): Flow<List<NoteDBModel>>

    @Query("SELECT * FROM ${Constants.NOTE_TABLE} WHERE noteId = :characterId ORDER BY modelId ASC")
    fun getNoteDatabase(characterId: Int): Flow<List<NoteDBModel>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addNoteDatabase(note: NoteDBModel)

    @Update
    fun updateNoteDatabase(note: NoteDBModel)

    @Delete
    fun deleteNoteDatabase(note: NoteDBModel)

    @Query("DELETE FROM ${Constants.NOTE_TABLE} WHERE noteId = :characterId")
    fun deleteAllNotesDatabase(characterId: Int)
}