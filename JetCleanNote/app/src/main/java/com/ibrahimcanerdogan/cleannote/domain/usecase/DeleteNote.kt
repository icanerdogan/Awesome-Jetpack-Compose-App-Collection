package com.ibrahimcanerdogan.cleannote.domain.usecase

import com.ibrahimcanerdogan.cleannote.data.model.Note
import com.ibrahimcanerdogan.cleannote.domain.repository.NoteRepository
import javax.inject.Inject

class DeleteNote @Inject constructor(
    private val repository: NoteRepository
) {

    suspend operator fun invoke(note: Note) {
        repository.deleteNoteRepository(note)
    }
}