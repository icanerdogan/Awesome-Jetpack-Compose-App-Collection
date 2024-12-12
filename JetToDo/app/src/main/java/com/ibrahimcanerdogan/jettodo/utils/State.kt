package com.ibrahimcanerdogan.jettodo.utils

sealed class State<out T> {
    data object Idle : State<Nothing>()
    data object Loading : State<Nothing>()
    data class Success<T>(val data: T) : State<T>()
    data class Error(val error: Throwable) : State<Nothing>()
}
