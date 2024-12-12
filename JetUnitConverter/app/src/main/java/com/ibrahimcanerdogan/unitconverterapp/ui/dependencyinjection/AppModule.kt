package com.ibrahimcanerdogan.unitconverterapp.ui.dependencyinjection

import android.content.Context
import androidx.room.Room
import com.ibrahimcanerdogan.unitconverterapp.data.database.ConverterDatabase
import com.ibrahimcanerdogan.unitconverterapp.data.repository.ConverterRepository
import com.ibrahimcanerdogan.unitconverterapp.data.repository.ConverterRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideConverterDatabase(
        @ApplicationContext context: Context
    ) : ConverterDatabase {
        return Room.databaseBuilder(
            context,
            ConverterDatabase::class.java,
            "converter_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideConverterRepository(converterDatabase: ConverterDatabase) : ConverterRepository {
        return ConverterRepositoryImpl(converterDatabase.converterDAO)
    }

}