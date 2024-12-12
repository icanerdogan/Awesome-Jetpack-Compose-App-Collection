package com.ibrahimcanerdogan.calculatorapp.ui.viewmodel

import com.ibrahimcanerdogan.calculatorapp.util.CalculatorOperation

data class CalculatorState(
    val number1: String = "",
    val number2: String = "",
    val result: String = "",
    val operation: CalculatorOperation? = null
)