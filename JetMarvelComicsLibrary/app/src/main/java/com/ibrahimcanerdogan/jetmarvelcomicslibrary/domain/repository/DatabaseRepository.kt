package com.ibrahimcanerdogan.jetmarvelcomicslibrary.domain.repository

import com.ibrahimcanerdogan.jetmarvelcomicslibrary.data.database.model.CharacterDBModel
import com.ibrahimcanerdogan.jetmarvelcomicslibrary.data.database.model.NoteDBModel
import kotlinx.coroutines.flow.Flow

interface DatabaseRepository {

    suspend fun getCharactersRepository(): Flow<List<CharacterDBModel>>

    suspend fun getSingleCharacterRepository(characterId: Int): Flow<CharacterDBModel>

    suspend fun addCharacterRepository(character: CharacterDBModel)

    suspend fun updateCharacterRepository(character: CharacterDBModel)

    suspend fun deleteCharacterRepository(character: CharacterDBModel)


    suspend fun getAllNotesRepository(): Flow<List<NoteDBModel>>

    suspend fun getNoteRepository(characterId: Int): Flow<List<NoteDBModel>>

    suspend fun addNoteRepository(note: NoteDBModel)

    suspend fun updateNoteRepository(note: NoteDBModel)

    suspend fun deleteNoteRepository(note: NoteDBModel)

    suspend fun deleteAllNotesRepository(character: CharacterDBModel)
}