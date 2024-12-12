package com.ibrahimcanerdogan.cleannote.domain.usecase

data class NoteUseCase(
    val getNotes: GetNotes,
    val deleteNote: DeleteNote,
    val addNote: AddNote,
    val getNote: GetNote
)
