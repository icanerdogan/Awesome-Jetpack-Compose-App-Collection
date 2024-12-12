package com.ibrahimcanerdogan.stockmarket.dependencyinjection

import com.ibrahimcanerdogan.stockmarket.data.parser.CSVParser
import com.ibrahimcanerdogan.stockmarket.data.parser.CompanyListParser
import com.ibrahimcanerdogan.stockmarket.data.parser.IntradayInfoParser
import com.ibrahimcanerdogan.stockmarket.domain.model.CompanyList
import com.ibrahimcanerdogan.stockmarket.domain.model.IntradayInfo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ParserRepository {

    @Binds
    @Singleton
    abstract fun bindCompanyListingsParser(
        companyListParser: CompanyListParser
    ): CSVParser<CompanyList>

    @Binds
    @Singleton
    abstract fun bindIntradayInfoParser(
        intradayInfoParser: IntradayInfoParser
    ): CSVParser<IntradayInfo>

}