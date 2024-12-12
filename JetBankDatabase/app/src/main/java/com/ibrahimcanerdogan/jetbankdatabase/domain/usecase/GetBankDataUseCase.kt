package com.ibrahimcanerdogan.jetbankdatabase.domain.usecase

import com.ibrahimcanerdogan.jetbankdatabase.domain.repository.BankRepository
import javax.inject.Inject

class GetBankDataUseCase @Inject constructor(
    private val bankRepository: BankRepository
) {
    fun invoke() = bankRepository.getBankDataRepository()
}