package com.ibrahimcanerdogan.jetmarvelcomicslibrary.data.repository

import com.ibrahimcanerdogan.jetmarvelcomicslibrary.data.database.dao.CharacterDao
import com.ibrahimcanerdogan.jetmarvelcomicslibrary.data.database.dao.NoteDao
import com.ibrahimcanerdogan.jetmarvelcomicslibrary.data.database.model.CharacterDBModel
import com.ibrahimcanerdogan.jetmarvelcomicslibrary.data.database.model.NoteDBModel
import com.ibrahimcanerdogan.jetmarvelcomicslibrary.domain.repository.DatabaseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DatabaseRepositoryImpl @Inject constructor(
    private val characterDao: CharacterDao,
    private val noteDao: NoteDao
): DatabaseRepository {

    override suspend fun getCharactersRepository(): Flow<List<CharacterDBModel>> = characterDao.getCharactersDatabase()

    override suspend fun getSingleCharacterRepository(characterId: Int): Flow<CharacterDBModel> = characterDao.getSingleCharacterDatabase(characterId)

    override suspend fun addCharacterRepository(character: CharacterDBModel) = characterDao.addCharacterDatabase(character)

    override suspend fun updateCharacterRepository(character: CharacterDBModel) = characterDao.updateCharacterDatabase(character)

    override suspend fun deleteCharacterRepository(character: CharacterDBModel) = characterDao.deleteCharacterDatabase(character)


    override suspend fun getAllNotesRepository() = noteDao.getAllNotesDatabase()

    override suspend fun getNoteRepository(characterId: Int) = noteDao.getNoteDatabase(characterId)

    override suspend fun addNoteRepository(note: NoteDBModel) = noteDao.addNoteDatabase(note)

    override suspend fun updateNoteRepository(note: NoteDBModel) = noteDao.updateNoteDatabase(note)

    override suspend fun deleteNoteRepository(note: NoteDBModel) = noteDao.deleteNoteDatabase(note)

    override suspend fun deleteAllNotesRepository(character: CharacterDBModel) = noteDao.deleteAllNotesDatabase(character.modelId)
}