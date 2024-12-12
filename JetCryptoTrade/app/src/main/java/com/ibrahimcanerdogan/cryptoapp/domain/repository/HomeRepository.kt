package com.ibrahimcanerdogan.cryptoapp.domain.repository

import com.ibrahimcanerdogan.cryptoapp.data.model.metadata.CryptoMetaDataResponse
import com.ibrahimcanerdogan.cryptoapp.util.Constants
import com.ibrahimcanerdogan.cryptoapp.util.Resource

interface HomeRepository {

    suspend fun getCryptoMetaData(
        apiKey: String = Constants.API_KEY,
        limit: String = Constants.LIMIT
    ): Resource<CryptoMetaDataResponse>
}