package com.ibrahimcanerdogan.unitconverterapp.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ConverterDAO {

    @Insert
    suspend fun insertConversionResult(entity: ConversionEntity)

    @Delete
    suspend fun deleteConversionResult(entity: ConversionEntity)

    @Query("DELETE FROM result_table")
    suspend fun deleteAllConversion()

    @Query("SELECT * FROM result_table")
    fun getAllConversionResults() : Flow<List<ConversionEntity>>
}