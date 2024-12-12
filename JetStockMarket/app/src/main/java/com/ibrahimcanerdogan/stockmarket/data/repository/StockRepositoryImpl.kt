package com.ibrahimcanerdogan.stockmarket.data.repository

import com.ibrahimcanerdogan.stockmarket.data.mapper.toCompanyInfo
import com.ibrahimcanerdogan.stockmarket.data.parser.CSVParser
import com.ibrahimcanerdogan.stockmarket.data.remote.StockAPI
import com.ibrahimcanerdogan.stockmarket.domain.model.CompanyDetail
import com.ibrahimcanerdogan.stockmarket.domain.model.CompanyList
import com.ibrahimcanerdogan.stockmarket.domain.model.IntradayInfo
import com.ibrahimcanerdogan.stockmarket.domain.repository.StockRepository
import com.ibrahimcanerdogan.stockmarket.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StockRepositoryImpl @Inject constructor(
    private val stockAPI: StockAPI,
    private val companyListingsParser: CSVParser<CompanyList>,
    private val intradayInfoParser: CSVParser<IntradayInfo>,
) : StockRepository {

    override suspend fun getCompanyListings(query: String): Flow<Resource<List<CompanyList>>> = flow {
        emit(Resource.Loading(true))
        val result = safeApiCall {
            val response = stockAPI.getListings()
            val listings = companyListingsParser.parse(response.byteStream())
            if (query.isNotEmpty()) {
                listings.filter { it.companyListName.contains(query, ignoreCase = true) }
            } else {
                listings
            }
        }
        emit(result)
        emit(Resource.Loading(false))
    }

    override suspend fun getIntradayInfo(symbol: String): Resource<List<IntradayInfo>> {
        return safeApiCall {
            val response = stockAPI.getIntradayInfo(symbol)
            intradayInfoParser.parse(response.byteStream())
        }
    }

    override suspend fun getCompanyInfo(symbol: String): Resource<CompanyDetail> {
        return safeApiCall {
            stockAPI.getCompanyInfo(symbol).toCompanyInfo()
        }
    }

    // Utility function to handle API calls
    private inline fun <T> safeApiCall(action: () -> T): Resource<T> {
        return try {
            Resource.Success(action())
        } catch (e: IOException) {
            logError(e)
            Resource.Error(message = "Network error occurred!")
        } catch (e: HttpException) {
            logError(e)
            Resource.Error(message = "Server error occurred!")
        }
    }

    private fun logError(e: Exception) {
        // Replace with a proper logging library
        println("Error: ${e.message}")
    }
}
