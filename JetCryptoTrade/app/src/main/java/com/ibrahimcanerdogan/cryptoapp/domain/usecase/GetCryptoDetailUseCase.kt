package com.ibrahimcanerdogan.cryptoapp.domain.usecase

import com.ibrahimcanerdogan.cryptoapp.data.model.detail.CryptoDetailResponse
import com.ibrahimcanerdogan.cryptoapp.domain.repository.DetailRepository
import com.ibrahimcanerdogan.cryptoapp.util.Resource
import javax.inject.Inject

class GetCryptoDetailUseCase @Inject constructor(
    private val detailRepository: DetailRepository
) {

    suspend fun execute(
        cryptoID: String
    ) : Resource<CryptoDetailResponse> {
        return detailRepository.getCryptoDetailData(cryptoID = cryptoID)
    }
}