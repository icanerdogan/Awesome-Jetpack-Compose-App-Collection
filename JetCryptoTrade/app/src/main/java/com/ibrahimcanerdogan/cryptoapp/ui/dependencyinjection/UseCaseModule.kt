package com.ibrahimcanerdogan.cryptoapp.ui.dependencyinjection

import com.ibrahimcanerdogan.cryptoapp.domain.repository.DetailRepository
import com.ibrahimcanerdogan.cryptoapp.domain.repository.HomeRepository
import com.ibrahimcanerdogan.cryptoapp.domain.usecase.GetCryptoDetailUseCase
import com.ibrahimcanerdogan.cryptoapp.domain.usecase.GetCryptoListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun provideGetCryptoListUseCase(
        homeRepository: HomeRepository
    ) : GetCryptoListUseCase {
        return GetCryptoListUseCase(homeRepository)
    }

    @Singleton
    @Provides
    fun provideGetCryptoDetailUseCase(
        detailRepository: DetailRepository
    ): GetCryptoDetailUseCase {
        return GetCryptoDetailUseCase(detailRepository)
    }
}