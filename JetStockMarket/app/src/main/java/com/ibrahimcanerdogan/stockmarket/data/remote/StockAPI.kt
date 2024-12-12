package com.ibrahimcanerdogan.stockmarket.data.remote

import com.ibrahimcanerdogan.stockmarket.data.remote.dto.CompanyDetailDto
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface StockAPI {

    @GET("query?function=LISTING_STATUS")
    suspend fun getListings(
        @Query("apikey") apiKey: String = API_KEY
    ): ResponseBody

    @GET("query?function=TIME_SERIES_INTRADAY&interval=60min&datatype=csv")
    suspend fun getIntradayInfo(
        @Query("symbol") symbol: String,
        @Query("apikey") apiKey: String = API_KEY
    ): ResponseBody

    @GET("query?function=OVERVIEW")
    suspend fun getCompanyInfo(
        @Query("symbol") symbol: String,
        @Query("apikey") apiKey: String = API_KEY
    ): CompanyDetailDto

    companion object {
        // HPBV1X4HWVIHLYT0
        const val API_KEY = "HPBV1X4HWVIHLYT0"
        const val BASE_URL = "https://alphavantage.co"
    }
}