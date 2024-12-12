package com.ibrahimcanerdogan.jetmovielibraryapp.ui.viewmodel.home

sealed class MovieHomeEvent {
    data class SearchEvent(val searchText : String) : MovieHomeEvent()
}