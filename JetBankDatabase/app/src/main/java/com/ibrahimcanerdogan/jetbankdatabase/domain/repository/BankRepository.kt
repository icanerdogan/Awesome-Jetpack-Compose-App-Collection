package com.ibrahimcanerdogan.jetbankdatabase.domain.repository

import com.ibrahimcanerdogan.jetbankdatabase.common.Resource
import com.ibrahimcanerdogan.jetbankdatabase.data.model.BankData
import kotlinx.coroutines.flow.Flow

interface BankRepository {
    fun getBankDataRepository() : Flow<Resource<BankData>>
}