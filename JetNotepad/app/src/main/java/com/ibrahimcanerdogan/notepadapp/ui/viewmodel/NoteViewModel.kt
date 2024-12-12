package com.ibrahimcanerdogan.notepadapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ibrahimcanerdogan.notepadapp.data.database.NoteEntity
import com.ibrahimcanerdogan.notepadapp.data.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val repository: NoteRepository
) : ViewModel() {

    private val _noteList = MutableStateFlow<List<NoteEntity>>(emptyList())
    val noteList = _noteList.asStateFlow()

    init {
        getAllNoteData()
    }

    fun getAllNoteData() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllNotesDatabase()
                .distinctUntilChanged()
                .collect { listNote ->
                    _noteList.value = listNote
                }
        }
    }

    fun addNote(noteEntity: NoteEntity) = viewModelScope.launch {
        repository.addNoteDatabase(noteEntity)
    }

    fun searchNote(searchText: String) = viewModelScope.launch {
        _noteList.value = repository.searchNoteDatabase(searchText)
    }

    fun updateNote(noteEntity: NoteEntity) = viewModelScope.launch {
        repository.updateNoteDatabase(noteEntity)
    }

    fun removeNote(noteEntity: NoteEntity) = viewModelScope.launch {
        repository.deleteNoteDatabase(noteEntity)
    }

    fun removeAllNote() = viewModelScope.launch {
        repository.deleteAllNotesDatabase()
    }
}