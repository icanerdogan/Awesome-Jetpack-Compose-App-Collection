package com.ibrahimcanerdogan.jetbankdatabase.data.repository.datasource

import com.ibrahimcanerdogan.jetbankdatabase.data.model.BankData

interface RemoteDataSource {
    suspend fun getBankDataSource() : BankData
}