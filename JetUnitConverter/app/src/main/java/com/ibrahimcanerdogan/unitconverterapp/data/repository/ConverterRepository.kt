package com.ibrahimcanerdogan.unitconverterapp.data.repository

import com.ibrahimcanerdogan.unitconverterapp.data.database.ConversionEntity
import kotlinx.coroutines.flow.Flow

interface ConverterRepository {
    suspend fun addResultDatabase(entity: ConversionEntity)
    suspend fun deleteResultDatabase(entity: ConversionEntity)
    suspend fun deleteAllResultDatabase()
    fun getAllResultDatabase(): Flow<List<ConversionEntity>>
}