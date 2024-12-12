package com.ibrahimcanerdogan.cleannote.ui.view.list

import com.ibrahimcanerdogan.cleannote.data.model.Note
import com.ibrahimcanerdogan.cleannote.domain.util.NoteOrder

sealed class NoteListEvent {
    data class Order(val noteOrder: NoteOrder): NoteListEvent()
    data class DeleteNote(val note: Note): NoteListEvent()
    data object RestoreNote: NoteListEvent()
    data object ToggleOrderSection: NoteListEvent()
}
