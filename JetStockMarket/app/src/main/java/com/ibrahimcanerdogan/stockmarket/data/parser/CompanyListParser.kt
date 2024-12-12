package com.ibrahimcanerdogan.stockmarket.data.parser

import com.ibrahimcanerdogan.stockmarket.domain.model.CompanyList
import com.opencsv.CSVReader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.io.InputStreamReader
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CompanyListParser @Inject constructor(): CSVParser<CompanyList> {

    override suspend fun parse(stream: InputStream): List<CompanyList> {
        val csvReader = CSVReader(InputStreamReader(stream))
        return withContext(Dispatchers.IO) {
            csvReader
                .readAll()
                .drop(1)
                .mapNotNull { line ->
                    val symbol = line.getOrNull(0)
                    val name = line.getOrNull(1)
                    val exchange = line.getOrNull(2)
                    CompanyList(
                        companyListName = name ?: return@mapNotNull null,
                        companyListSymbol = symbol ?: return@mapNotNull null,
                        companyListExchange = exchange ?: return@mapNotNull null
                    )
                }
                .also {
                    csvReader.close()
                }
        }
    }
}