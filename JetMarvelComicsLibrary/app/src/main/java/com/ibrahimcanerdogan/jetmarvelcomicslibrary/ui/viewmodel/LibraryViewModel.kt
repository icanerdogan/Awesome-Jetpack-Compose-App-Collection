package com.ibrahimcanerdogan.jetmarvelcomicslibrary.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ibrahimcanerdogan.jetmarvelcomicslibrary.data.database.model.CharacterDBModel
import com.ibrahimcanerdogan.jetmarvelcomicslibrary.data.database.model.NoteDBModel
import com.ibrahimcanerdogan.jetmarvelcomicslibrary.data.model.CharacterResult
import com.ibrahimcanerdogan.jetmarvelcomicslibrary.data.model.NoteResult
import com.ibrahimcanerdogan.jetmarvelcomicslibrary.domain.repository.DatabaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LibraryViewModel @Inject constructor(
    private val databaseRepository: DatabaseRepository
): ViewModel() {

    private val currentCharacter = MutableStateFlow<CharacterDBModel?>(null)
    val collection = MutableStateFlow<List<CharacterDBModel>>(listOf())
    val notes = MutableStateFlow<List<NoteDBModel>>(listOf())

    init {
        getCollection()
        getNotes()
    }

    private fun getCollection() {
        viewModelScope.launch {
            databaseRepository.getCharactersRepository().collect {
                collection.value = it
            }
        }
    }

    fun setCurrentCharacterId(characterId: Int?) {
        characterId?.let {
            viewModelScope.launch {
                databaseRepository.getSingleCharacterRepository(it).collect {
                    currentCharacter.value = it
                }
            }
        }
    }

    fun addCharacter(character: CharacterResult) {
        viewModelScope.launch(Dispatchers.IO) {
            databaseRepository.addCharacterRepository(CharacterDBModel.fromCharacter(character))
        }
    }

    fun deleteCharacter(character: CharacterDBModel) {
        viewModelScope.launch(Dispatchers.IO) {
            databaseRepository.deleteAllNotesRepository(character)
            databaseRepository.deleteCharacterRepository(character)
        }
    }

    private fun getNotes() {
        viewModelScope.launch {
            databaseRepository.getAllNotesRepository().collect {
                notes.value = it
            }
        }
    }

    fun addNote(noteResult: NoteResult) {
        viewModelScope.launch(Dispatchers.IO) {
            databaseRepository.addNoteRepository(NoteDBModel.fromNote(noteResult))
        }
    }

    fun deleteNote(note: NoteDBModel) {
        viewModelScope.launch(Dispatchers.IO) {
            databaseRepository.deleteNoteRepository(note)
        }
    }
}




