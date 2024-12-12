package com.ibrahimcanerdogan.jetmovielibraryapp.ui.viewmodel.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ibrahimcanerdogan.jetmovielibraryapp.domain.usecase.GetAllSearchedMoviesUseCase
import com.ibrahimcanerdogan.jetmovielibraryapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieHomeViewModel @Inject constructor(
    private val getAllSearchedMoviesUseCase: GetAllSearchedMoviesUseCase
): ViewModel() {

    private val _splashIsLoading = MutableStateFlow(true)
    val splashIsLoading = _splashIsLoading.asStateFlow()

    private val _state = mutableStateOf(MovieHomeState())
    val state : State<MovieHomeState>
        get() = _state

    private var job : Job? = null

    init {
        loadAllSearchMovies(_state.value.stateSearch ?: "Godfather")
    }

    private fun loadAllSearchMovies(searchText : String) {
        job?.cancel()

        viewModelScope.launch(Dispatchers.IO) {
            job = getAllSearchedMoviesUseCase.execute(searchText).onEach {
                when(it) {
                    is Resource.Success -> {
                        _state.value = MovieHomeState(stateMovieList = it.data ?: emptyList())
                        _splashIsLoading.value = false
                    }
                    is Resource.Error -> {
                        _state.value = MovieHomeState(stateError = it.message ?: "Error!")
                    }
                    is Resource.Loading -> {
                        _state.value = MovieHomeState(stateIsLoading = true)
                    }
                }
            }.launchIn(viewModelScope)
         }
    }

    fun onEvent(movieEvent: MovieHomeEvent) {
        when(movieEvent) {
            is MovieHomeEvent.SearchEvent -> {
                loadAllSearchMovies(movieEvent.searchText)
            }
        }
    }
}