package com.ibrahimcanerdogan.jetmovielibraryapp.ui.viewmodel.detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ibrahimcanerdogan.jetmovielibraryapp.domain.usecase.GetMovieDetailDataUseCase
import com.ibrahimcanerdogan.jetmovielibraryapp.util.Constants
import com.ibrahimcanerdogan.jetmovielibraryapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getMovieDetailDataUseCase: GetMovieDetailDataUseCase,
    private val stateHandle: SavedStateHandle
): ViewModel() {

    private val _stateDetail = mutableStateOf(MovieDetailState())
    val stateDetail : State<MovieDetailState>
        get() = _stateDetail

    init {
        stateHandle.get<String>(Constants.IMDB_ID)?.let {
            loadMovieDetail(it)
        }
    }

    private fun loadMovieDetail(movieImdbID : String) {
        val movieID = movieImdbID.replace("{", "")
        val newMovieID = movieID.replace("}", "")

        viewModelScope.launch {
            getMovieDetailDataUseCase.execute(newMovieID).onEach {
                when(it) {
                    is Resource.Success -> {
                        _stateDetail.value = MovieDetailState(stateMovieDetail = it.data)
                    }
                    is Resource.Error -> {
                        _stateDetail.value = MovieDetailState(stateError = it.message ?: "Error!")
                    }
                    is Resource.Loading -> {
                        _stateDetail.value = MovieDetailState(stateIsLoading = true)
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

}