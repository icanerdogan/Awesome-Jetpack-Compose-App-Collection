package com.ibrahimcanerdogan.stockmarket.ui.screen.detail

import com.ibrahimcanerdogan.stockmarket.domain.model.CompanyDetail
import com.ibrahimcanerdogan.stockmarket.domain.model.IntradayInfo

data class CompanyDetailState(
    val detailIntradayInfo: List<IntradayInfo> = emptyList(),
    val detailCompany: CompanyDetail? = null,
    val detailIsLoading: Boolean = false,
    val detailError: String? = null
)