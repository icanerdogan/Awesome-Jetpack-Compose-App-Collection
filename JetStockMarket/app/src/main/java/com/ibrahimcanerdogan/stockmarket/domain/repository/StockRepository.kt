package com.ibrahimcanerdogan.stockmarket.domain.repository

import com.ibrahimcanerdogan.stockmarket.domain.model.CompanyDetail
import com.ibrahimcanerdogan.stockmarket.domain.model.CompanyList
import com.ibrahimcanerdogan.stockmarket.domain.model.IntradayInfo
import com.ibrahimcanerdogan.stockmarket.util.Resource
import kotlinx.coroutines.flow.Flow

interface StockRepository {

    suspend fun getCompanyListings(
        query: String
    ): Flow<Resource<List<CompanyList>>>

    suspend fun getIntradayInfo(
        symbol: String
    ): Resource<List<IntradayInfo>>

    suspend fun getCompanyInfo(
        symbol: String
    ): Resource<CompanyDetail>
}