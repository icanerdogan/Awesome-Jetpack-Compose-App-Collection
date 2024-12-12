package com.ibrahimcanerdogan.unitconverterapp.data.repository

import com.ibrahimcanerdogan.unitconverterapp.data.database.ConversionEntity
import com.ibrahimcanerdogan.unitconverterapp.data.database.ConverterDAO
import kotlinx.coroutines.flow.Flow

class ConverterRepositoryImpl(
    private val converterDAO: ConverterDAO
) : ConverterRepository {

    override suspend fun addResultDatabase(entity: ConversionEntity) {
        converterDAO.insertConversionResult(entity)
    }

    override suspend fun deleteResultDatabase(entity: ConversionEntity) {
        converterDAO.deleteConversionResult(entity)
    }

    override suspend fun deleteAllResultDatabase() {
        converterDAO.deleteAllConversion()
    }

    override fun getAllResultDatabase(): Flow<List<ConversionEntity>> {
        return converterDAO.getAllConversionResults()
    }
}