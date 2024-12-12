package com.ibrahimcanerdogan.unitconverterapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ConversionEntity::class], version = 1)
abstract class ConverterDatabase : RoomDatabase() {
    abstract val converterDAO: ConverterDAO
}