package com.ibrahimcanerdogan.notepadapp.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.UUID

@Entity(tableName = "notes_table")
data class NoteEntity(
    @PrimaryKey
    @ColumnInfo("note_id")
    val noteID: String = UUID.randomUUID().toString(),
    @ColumnInfo("note_title")
    val noteTitle: String,
    @ColumnInfo("note_subtitle")
    val noteSubtitle: String,
    @ColumnInfo("note_description")
    val noteDescription: String,
    @ColumnInfo("note_image")
    val noteImagePath: String?,
    @ColumnInfo("note_date")
    val noteEntryDate: String = currentEntryNoteDate()
)

fun currentEntryNoteDate(): String {
    val calendar = Calendar.getInstance()

    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    return dateFormat.format(calendar.time)
}
