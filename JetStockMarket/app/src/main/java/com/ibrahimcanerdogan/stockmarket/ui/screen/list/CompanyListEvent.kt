package com.ibrahimcanerdogan.stockmarket.ui.screen.list

sealed class CompanyListEvent {
    data object Refresh: CompanyListEvent()
    data class OnSearchQueryChange(val query: String): CompanyListEvent()
}
