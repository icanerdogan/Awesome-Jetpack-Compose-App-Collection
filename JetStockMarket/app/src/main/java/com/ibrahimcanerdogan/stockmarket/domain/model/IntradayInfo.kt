package com.ibrahimcanerdogan.stockmarket.domain.model

import java.time.LocalDateTime

data class IntradayInfo(
    val intradayDate: LocalDateTime,
    val intradayClose: Double
)
