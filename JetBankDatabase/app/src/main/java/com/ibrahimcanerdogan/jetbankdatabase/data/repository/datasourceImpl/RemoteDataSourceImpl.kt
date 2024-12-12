package com.ibrahimcanerdogan.jetbankdatabase.data.repository.datasourceImpl

import com.ibrahimcanerdogan.jetbankdatabase.data.model.BankData
import com.ibrahimcanerdogan.jetbankdatabase.data.network.BankAPIService
import com.ibrahimcanerdogan.jetbankdatabase.data.repository.datasource.RemoteDataSource
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val bankAPIService: BankAPIService
) : RemoteDataSource {

    override suspend fun getBankDataSource(): BankData {
        return bankAPIService.getBankDataNetwork()
    }

}