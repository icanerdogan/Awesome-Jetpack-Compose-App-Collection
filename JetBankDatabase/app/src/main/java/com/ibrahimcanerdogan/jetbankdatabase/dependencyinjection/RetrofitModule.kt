package com.ibrahimcanerdogan.jetbankdatabase.dependencyinjection

import com.ibrahimcanerdogan.jetbankdatabase.common.Constants
import com.ibrahimcanerdogan.jetbankdatabase.data.network.BankAPIService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    @Singleton
    fun provideBankAPIService(retrofit: Retrofit) : BankAPIService {
        return retrofit.create(BankAPIService::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}