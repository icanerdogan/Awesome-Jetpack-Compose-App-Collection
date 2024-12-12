package com.ibrahimcanerdogan.stockmarket.data.mapper

import com.ibrahimcanerdogan.stockmarket.data.remote.dto.CompanyDetailDto
import com.ibrahimcanerdogan.stockmarket.domain.model.CompanyDetail

fun CompanyDetailDto.toCompanyInfo(): CompanyDetail {
    return CompanyDetail(
        companyDetailSymbol = symbol ?: "",
        companyDetailDescription = description ?: "",
        companyDetailName = name ?: "",
        companyDetailCountry = country ?: "",
        companyDetailIndustry = industry ?: ""
    )
}