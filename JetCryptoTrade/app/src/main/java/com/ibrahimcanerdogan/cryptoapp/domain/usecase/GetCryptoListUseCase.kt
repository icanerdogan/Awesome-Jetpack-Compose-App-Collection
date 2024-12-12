package com.ibrahimcanerdogan.cryptoapp.domain.usecase

import com.ibrahimcanerdogan.cryptoapp.data.model.metadata.CryptoMetaDataResponse
import com.ibrahimcanerdogan.cryptoapp.domain.repository.HomeRepository
import com.ibrahimcanerdogan.cryptoapp.util.Resource
import javax.inject.Inject

class GetCryptoListUseCase @Inject constructor(
    private val homeRepository: HomeRepository
) {

    suspend fun execute() : Resource<CryptoMetaDataResponse> {
        return homeRepository.getCryptoMetaData()
    }
}