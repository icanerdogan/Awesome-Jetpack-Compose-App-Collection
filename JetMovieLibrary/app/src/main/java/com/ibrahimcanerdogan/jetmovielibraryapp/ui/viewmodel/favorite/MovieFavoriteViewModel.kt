package com.ibrahimcanerdogan.jetmovielibraryapp.ui.viewmodel.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ibrahimcanerdogan.jetmovielibraryapp.data.database.favorite.MovieFavoriteEntity
import com.ibrahimcanerdogan.jetmovielibraryapp.domain.usecase.GetMovieFavoriteDataLocalUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieFavoriteViewModel @Inject constructor(
    private val useCase: GetMovieFavoriteDataLocalUseCase
) : ViewModel() {

    private val _favoriteMovies = MutableLiveData<List<MovieFavoriteEntity>>()
    val favoriteMovies: LiveData<List<MovieFavoriteEntity>>
        get() = _favoriteMovies

    init {
        loadFavoriteMovies()
    }

    fun loadFavoriteMovies() = viewModelScope.launch(Dispatchers.IO) {
        _favoriteMovies.postValue(useCase.executeList())
    }

    fun addFavorite(movieFavoriteEntity: MovieFavoriteEntity) = viewModelScope.launch(Dispatchers.IO) {
        useCase.executeInsert(movieFavoriteEntity)
        _favoriteMovies.postValue(useCase.executeList())
    }

    fun deleteFavorite(movieFavoriteEntity: MovieFavoriteEntity) = viewModelScope.launch(Dispatchers.IO) {
        useCase.executeDelete(movieFavoriteEntity)
        _favoriteMovies.postValue(useCase.executeList())
    }
}