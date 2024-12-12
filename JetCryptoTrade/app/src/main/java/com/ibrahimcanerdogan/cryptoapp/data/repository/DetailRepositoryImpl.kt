package com.ibrahimcanerdogan.cryptoapp.data.repository

import com.ibrahimcanerdogan.cryptoapp.data.model.detail.CryptoDetailResponse
import com.ibrahimcanerdogan.cryptoapp.data.network.APIService
import com.ibrahimcanerdogan.cryptoapp.domain.repository.DetailRepository
import com.ibrahimcanerdogan.cryptoapp.util.Resource
import javax.inject.Inject

class DetailRepositoryImpl @Inject constructor(
    private val apiService: APIService
) : DetailRepository  {

    override suspend fun getCryptoDetailData(
        apiKey: String,
        cryptoID: String
    ): Resource<CryptoDetailResponse> {
        val response = try {
            apiService.getCryptoDetailData(apiKey, cryptoID)
        } catch (e: Exception) {
            return Resource.Error("Something went wrong!")
        }
        return Resource.Success(response)
    }
}