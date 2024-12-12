package com.ibrahimcanerdogan.cryptoapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.ibrahimcanerdogan.cryptoapp.data.model.detail.CryptoDetailResponse
import com.ibrahimcanerdogan.cryptoapp.domain.usecase.GetCryptoDetailUseCase
import com.ibrahimcanerdogan.cryptoapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CryptoDetailViewModel @Inject constructor(
    private val getCryptoDetailUseCase: GetCryptoDetailUseCase
) : ViewModel() {

    suspend fun loadCryptoDetailData(
        cryptoID: String
    ) : Resource<CryptoDetailResponse> {
        return getCryptoDetailUseCase.execute(cryptoID)
    }

}