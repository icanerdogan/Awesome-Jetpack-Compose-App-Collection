package com.ibrahimcanerdogan.cryptoapp.util

import java.text.SimpleDateFormat
import java.util.Locale

object DateTimeUtils {

    fun convertDate(date: String) : String {
        val inputDate = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        val outputDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

        val date = inputDate.parse(date)
        return outputDate.format(date)
    }
}