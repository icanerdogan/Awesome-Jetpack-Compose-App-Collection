package com.ibrahimcanerdogan.cryptoapp.domain.repository

import com.ibrahimcanerdogan.cryptoapp.data.model.detail.CryptoDetailResponse
import com.ibrahimcanerdogan.cryptoapp.util.Constants
import com.ibrahimcanerdogan.cryptoapp.util.Resource

interface DetailRepository {

    suspend fun getCryptoDetailData(
        apiKey: String = Constants.API_KEY,
        cryptoID: String
    ) : Resource<CryptoDetailResponse>
}