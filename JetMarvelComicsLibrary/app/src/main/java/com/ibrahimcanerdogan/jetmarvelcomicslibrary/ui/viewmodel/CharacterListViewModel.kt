package com.ibrahimcanerdogan.jetmarvelcomicslibrary.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ibrahimcanerdogan.jetmarvelcomicslibrary.data.connectivity.ConnectivityMonitor
import com.ibrahimcanerdogan.jetmarvelcomicslibrary.data.network.NetworkResult
import com.ibrahimcanerdogan.jetmarvelcomicslibrary.domain.repository.NetworkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val networkRepository: NetworkRepository,
    connectivityMonitor: ConnectivityMonitor
): ViewModel() {

    val result = networkRepository.characters
    val queryText = MutableStateFlow("")
    private val queryInput = Channel<String>(Channel.CONFLATED)
    val characterDetails = networkRepository.characterDetails
    val networkAvailable = connectivityMonitor

    init {
        retrieveCharacters()
    }

    private fun retrieveCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            queryInput.receiveAsFlow()
                .filter { validateQuery(it) }
                .debounce(1000)
                .collect {
                    networkRepository.getMarvelCharactersRepository(it)
                }
        }
    }

    private fun validateQuery(query: String): Boolean = query.length >= 2

    fun onQueryUpdate(input: String) {
        if (input.isEmpty()) result.value = NetworkResult.Initial()
        queryText.value = input
        queryInput.trySend(input)
    }

    fun retrieveSingleCharacter(id: Int) {
        networkRepository.getSingleMarvelCharacterRepository(id)
    }
}
