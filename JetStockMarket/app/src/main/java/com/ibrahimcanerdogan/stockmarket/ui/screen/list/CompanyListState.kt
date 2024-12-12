package com.ibrahimcanerdogan.stockmarket.ui.screen.list

import com.ibrahimcanerdogan.stockmarket.domain.model.CompanyList

data class CompanyListState(
    val listCompany: List<CompanyList> = emptyList(),
    val listIsLoading: Boolean = false,
    val listIsRefreshing: Boolean = false,
    val listSearchQuery: String = ""
)