package com.ibrahimcanerdogan.cryptoapp.data.repository

import com.ibrahimcanerdogan.cryptoapp.data.model.metadata.CryptoMetaDataResponse
import com.ibrahimcanerdogan.cryptoapp.data.network.APIService
import com.ibrahimcanerdogan.cryptoapp.domain.repository.HomeRepository
import com.ibrahimcanerdogan.cryptoapp.util.Resource
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val apiService: APIService
) : HomeRepository {

    override suspend fun getCryptoMetaData(
        apiKey: String,
        limit: String
    ): Resource<CryptoMetaDataResponse> {
        val response = try {
            apiService.getCryptoMetaData(apiKey, limit)
        } catch (e: Exception) {
            return Resource.Error("Something went wrong!")
        }
        return Resource.Success(response)
    }
}