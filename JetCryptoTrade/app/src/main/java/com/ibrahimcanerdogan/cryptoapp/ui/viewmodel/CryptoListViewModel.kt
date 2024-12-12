package com.ibrahimcanerdogan.cryptoapp.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ibrahimcanerdogan.cryptoapp.data.model.metadata.CryptoMetaData
import com.ibrahimcanerdogan.cryptoapp.domain.usecase.GetCryptoListUseCase
import com.ibrahimcanerdogan.cryptoapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CryptoListViewModel @Inject constructor(
    private val getCryptoListUseCase: GetCryptoListUseCase
) : ViewModel() {

    val cryptoMetaDataResponse = mutableStateOf<List<CryptoMetaData>>(listOf())
    var onError = mutableStateOf("")
    var isLoading = mutableStateOf(false)

    private var initialCryptoList = listOf<CryptoMetaData>()
    private var isSearchStarting = true

    init {
        loadAllCryptoMetaData()
    }

    fun loadAllCryptoMetaData() = viewModelScope.launch {
        isLoading.value = true
        when(val request = getCryptoListUseCase.execute()) {
            is Resource.Success -> {
                cryptoMetaDataResponse.value = request.data?.data ?: listOf()
                isLoading.value = false
            }
            is Resource.Error -> {
                onError.value = request.message.toString()
                isLoading.value = false
            }
            is Resource.Loading -> {
                isLoading.value = true
            }
        }
    }

    fun searchCryptoDataList(searchText: String) {
        val listToSearch = if (isSearchStarting) {
            cryptoMetaDataResponse.value
        } else {
            initialCryptoList
        }

        viewModelScope.launch(Dispatchers.Default) {
            if (searchText.isEmpty()) {
                cryptoMetaDataResponse.value = initialCryptoList
                isSearchStarting = true
                return@launch
            }

            val results = listToSearch.filter {
                it.metaName.contains(searchText.trim(), ignoreCase = true)
            }

            if (isSearchStarting) {
                initialCryptoList = cryptoMetaDataResponse.value
                isSearchStarting = false
            }

            cryptoMetaDataResponse.value = results
        }
    }
}