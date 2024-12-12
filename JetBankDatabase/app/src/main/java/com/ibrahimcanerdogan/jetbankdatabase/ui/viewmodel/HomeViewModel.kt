package com.ibrahimcanerdogan.jetbankdatabase.ui.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ibrahimcanerdogan.jetbankdatabase.common.Resource
import com.ibrahimcanerdogan.jetbankdatabase.data.model.BankDataItem
import com.ibrahimcanerdogan.jetbankdatabase.domain.usecase.GetBankDataUseCase
import com.ibrahimcanerdogan.jetbankdatabase.ui.view.home.HomeScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase: GetBankDataUseCase
) : ViewModel() {

    private val _homeSate = mutableStateOf(HomeScreenState())
    val homeState: State<HomeScreenState> = _homeSate

    private val _filteredBankList = mutableStateOf<List<BankDataItem>>(emptyList())
    val filteredBankList: State<List<BankDataItem>> = _filteredBankList

    init {
        getBankList()
    }

    private fun getBankList() = viewModelScope.launch {
        useCase.invoke().collect {
            when(it) {
                is Resource.Error -> {
                    _homeSate.value = HomeScreenState(isLoading = false, errorMessage = it.message ?: "Error!")
                }
                is Resource.Loading -> {
                    _homeSate.value = HomeScreenState(isLoading = true)
                }
                is Resource.Success -> {
                    delay(1000)
                    _homeSate.value = HomeScreenState(isLoading = false, bankData = it.data)
                    _filteredBankList.value = it.data ?: emptyList()
                }
            }
        }
    }

    fun filterBankList(query: String) {
        _filteredBankList.value = _homeSate.value.bankData?.filter {
            (it.bankDistrict?.contains(query, ignoreCase = true) == true) || (it.bankCity?.contains(query, ignoreCase = true) == true)
        } ?: emptyList()
    }
}