package com.ibrahimcanerdogan.jetbankdatabase.data.network

import com.ibrahimcanerdogan.jetbankdatabase.data.model.BankData
import retrofit2.http.GET

interface BankAPIService {

    @GET("bankdata")
    suspend fun getBankDataNetwork() : BankData
}