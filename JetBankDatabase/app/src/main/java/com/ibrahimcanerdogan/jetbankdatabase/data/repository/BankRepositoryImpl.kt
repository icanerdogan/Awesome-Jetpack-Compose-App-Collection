package com.ibrahimcanerdogan.jetbankdatabase.data.repository

import com.ibrahimcanerdogan.jetbankdatabase.common.Resource
import com.ibrahimcanerdogan.jetbankdatabase.data.model.BankData
import com.ibrahimcanerdogan.jetbankdatabase.data.repository.datasource.RemoteDataSource
import com.ibrahimcanerdogan.jetbankdatabase.domain.repository.BankRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BankRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : BankRepository {

    override fun getBankDataRepository(): Flow<Resource<BankData>> = flow {
        emit(Resource.Loading())
        val result = runCatching {
            remoteDataSource.getBankDataSource()
        }.onFailure {
            emit(Resource.Error(it.message ?: "Error!"))
        }.getOrNull()

        result?.let {
            emit(Resource.Success(it))
        }
    }
}