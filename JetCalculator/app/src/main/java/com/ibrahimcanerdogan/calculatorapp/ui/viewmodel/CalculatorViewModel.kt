package com.ibrahimcanerdogan.calculatorapp.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.ibrahimcanerdogan.calculatorapp.util.CalculatorAction
import com.ibrahimcanerdogan.calculatorapp.util.CalculatorOperation

class CalculatorViewModel : ViewModel() {

    var state by mutableStateOf(CalculatorState())

    fun onAction(action: CalculatorAction) {
        when(action) {
            is CalculatorAction.Number -> enterNumber(action.number)
            is CalculatorAction.Delete -> delete()
            is CalculatorAction.Clear -> state = CalculatorState()
            is CalculatorAction.Operation -> enterOperation(action.operation)
            is CalculatorAction.Decimal -> enterDecimal()
            is CalculatorAction.Calculate -> calculate()
        }
    }

    private fun calculate() {
        val number1 = state.number1.toDoubleOrNull()
        val number2 = state.number2.toDoubleOrNull()

        if (number1 != null && number2 != null) {
            val result = when(state.operation) {
                is CalculatorOperation.Add -> number1 + number2
                is CalculatorOperation.Subtract -> number1 - number2
                is CalculatorOperation.Multiply -> number1 * number2
                is CalculatorOperation.Divide -> number1 / number2
                null -> return
            }

            state = state.copy(
                number1 = "",
                number2 = "",
                result = result.toString().take(12),
                operation = null
            )
        }
    }

    private fun enterOperation(operation: CalculatorOperation) {
        if (state.number1.isNotBlank()) {
            state = state.copy(operation = operation)
        }
    }

    private fun delete() {
        when {
            state.number1.isNotBlank() -> {
                state = state.copy(number1 = state.number1.dropLast(1))
            }
            state.number2.isNotBlank() -> {
                state = state.copy(number2 = state.number2.dropLast(1))
            }
            state.operation != null -> {
                state = state.copy(operation = null)
            }
        }
    }

    private fun enterDecimal() {
        if (
            state.operation == null &&
            !state.number1.contains(".") &&
            state.number1.isNotBlank()
        ) {
            state = state.copy(number1 = state.number1 + ".")
        } else if (!state.number2.contains(".") && state.number2.isNotBlank()) {
            state = state.copy(number2 = state.number2 + ".")
        }
    }

    private fun enterNumber(number: Int) {
        if (state.operation == null) {
            if (state.number1.length >= MAX_NUM_LENGTH) return
            state = state.copy(number1 = state.number1 + number)
            return
        }

        if (state.number2.length >= MAX_NUM_LENGTH) return
        state = state.copy(number2 = state.number2 + number)
    }

    companion object {
        private const val MAX_NUM_LENGTH = 8
    }
}