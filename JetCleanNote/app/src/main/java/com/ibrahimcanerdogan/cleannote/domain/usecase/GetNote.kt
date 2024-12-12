package com.ibrahimcanerdogan.cleannote.domain.usecase

import com.ibrahimcanerdogan.cleannote.data.model.Note
import com.ibrahimcanerdogan.cleannote.domain.repository.NoteRepository
import javax.inject.Inject

class GetNote @Inject constructor(
    private val repository: NoteRepository
) {

    suspend operator fun invoke(id: Int): Note? {
        return repository.getSingleNoteRepository(id)
    }
}