package com.ibrahimcanerdogan.jetmarvelcomicslibrary.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ibrahimcanerdogan.jetmarvelcomicslibrary.data.model.NoteResult
import com.ibrahimcanerdogan.jetmarvelcomicslibrary.utils.Constants

@Entity(tableName = Constants.NOTE_TABLE)
data class NoteDBModel(
    @PrimaryKey(autoGenerate = true)
    val modelId: Int,
    val noteId: Int,
    val noteTitle: String,
    val noteText: String
) {
    companion object {
        fun fromNote(noteResult: NoteResult) =
            NoteDBModel(
                modelId = 0,
                noteId = noteResult.noteResultCharId,
                noteTitle = noteResult.noteResultTitle,
                noteText = noteResult.noteResultText
            )
    }
}