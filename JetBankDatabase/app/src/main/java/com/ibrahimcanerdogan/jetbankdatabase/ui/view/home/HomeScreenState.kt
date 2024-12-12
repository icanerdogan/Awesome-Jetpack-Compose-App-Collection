package com.ibrahimcanerdogan.jetbankdatabase.ui.view.home

import com.ibrahimcanerdogan.jetbankdatabase.data.model.BankDataItem

data class HomeScreenState(
    val isLoading: Boolean = false,
    val bankData: ArrayList<BankDataItem>? = null,
    val errorMessage: String? = null
)