package com.ibrahimcanerdogan.cleannote.domain.util

sealed class OrderType {
    data object Ascending: OrderType()
    data object Descending: OrderType()
}
