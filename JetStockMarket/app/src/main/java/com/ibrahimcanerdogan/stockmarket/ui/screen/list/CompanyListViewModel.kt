package com.ibrahimcanerdogan.stockmarket.ui.screen.list

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ibrahimcanerdogan.stockmarket.domain.repository.StockRepository
import com.ibrahimcanerdogan.stockmarket.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CompanyListViewModel @Inject constructor(
    private val repository: StockRepository
): ViewModel() {

    var state by mutableStateOf(CompanyListState())
    private var searchJob: Job? = null

    init {
        getCompanyListings()
    }

    fun onEvent(event: CompanyListEvent) {
        when(event) {
            is CompanyListEvent.Refresh -> {
                getCompanyListings()
            }
            is CompanyListEvent.OnSearchQueryChange -> {
                state = state.copy(listSearchQuery = event.query)
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    delay(500L)
                    getCompanyListings()
                }
            }
        }
    }

    private fun getCompanyListings(
        query: String = state.listSearchQuery
    ) {
        viewModelScope.launch {
            repository
                .getCompanyListings(query)
                .collect { result ->
                    when(result) {
                        is Resource.Success -> {
                            result.data?.let { listings ->
                                state = state.copy(listCompany = listings)
                            }
                        }
                        is Resource.Error -> Log.e("TAG", result.message ?: "Error message is null!")
                        is Resource.Loading -> {
                            state = state.copy(listIsLoading = result.isLoading)
                        }
                    }
                }
        }
    }
}