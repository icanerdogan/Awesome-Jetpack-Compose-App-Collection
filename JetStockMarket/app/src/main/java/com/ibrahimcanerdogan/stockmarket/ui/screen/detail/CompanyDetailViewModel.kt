package com.ibrahimcanerdogan.stockmarket.ui.screen.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ibrahimcanerdogan.stockmarket.domain.repository.StockRepository
import com.ibrahimcanerdogan.stockmarket.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CompanyDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: StockRepository
): ViewModel() {

    var state by mutableStateOf(CompanyDetailState())

    init {
        viewModelScope.launch {
            val symbol = savedStateHandle.get<String>("symbol") ?: return@launch
            state = state.copy(detailIsLoading = true)
            val companyInfoResult = async { repository.getCompanyInfo(symbol) }
            val intradayInfoResult = async { repository.getIntradayInfo(symbol) }
            when(val result = companyInfoResult.await()) {
                is Resource.Success -> {
                    state = state.copy(
                        detailCompany = result.data,
                        detailIsLoading = false,
                        detailError = null
                    )
                }
                is Resource.Error -> {
                    state = state.copy(
                        detailIsLoading = false,
                        detailError = result.message,
                        detailCompany = null
                    )
                }
                else -> Unit
            }
            when(val result = intradayInfoResult.await()) {
                is Resource.Success -> {
                    state = state.copy(
                        detailIntradayInfo = result.data ?: emptyList(),
                        detailIsLoading = false,
                        detailError = null
                    )
                }
                is Resource.Error -> {
                    state = state.copy(
                        detailIsLoading = false,
                        detailError = result.message,
                        detailCompany = null
                    )
                }
                else -> Unit
            }
        }
    }
}