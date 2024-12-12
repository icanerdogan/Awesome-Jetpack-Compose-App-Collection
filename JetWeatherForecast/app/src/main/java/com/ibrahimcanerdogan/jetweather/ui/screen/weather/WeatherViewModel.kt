package com.ibrahimcanerdogan.jetweather.ui.screen.weather

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ibrahimcanerdogan.jetweather.domain.location.LocationTracker
import com.ibrahimcanerdogan.jetweather.domain.usecase.GetDailyForecastUseCase
import com.ibrahimcanerdogan.jetweather.domain.usecase.GetHourlyForecastUseCase
import com.ibrahimcanerdogan.jetweather.ui.screen.weather.state.WeatherUIState
import com.ibrahimcanerdogan.jetweather.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val getDailyForecastUseCase: GetDailyForecastUseCase,
    private val getHourlyForecastUseCase: GetHourlyForecastUseCase,
    private val locationTracker: LocationTracker
): ViewModel() {

    var state by mutableStateOf(WeatherUIState())
        private set

    fun loadHourlyForecastInfo() {
        viewModelScope.launch {
            state.copy(isLoading = true, error = null)

            val location = locationTracker.getCurrentLocation()
            if (location == null) {
                state.copy(isLoading = false, error = "Location unavailable.")
                return@launch
            }

            getHourlyForecastUseCase(location.latitude, location.longitude)
                .onEach { resource ->
                    when (resource) {
                        is Resource.Success -> state.copy(
                            forecastHourly = resource.data,
                            isLoading = false
                        )
                        is Resource.Loading -> state.copy(isLoading = true)
                        is Resource.Error -> state.copy(
                            isLoading = false,
                            error = resource.message ?: "An unknown error occurred."
                        )
                    }
                }
                .catch { exception ->
                    state.copy(
                        isLoading = false,
                        error = exception.message ?: "An unexpected error occurred."
                    )
                }
                .launchIn(this)
        }
    }

    fun loadDailyForecastInfo() {
        viewModelScope.launch {
            state.copy(isLoading = true, error = null)

            val location = locationTracker.getCurrentLocation()
            if (location == null) {
                state.copy(isLoading = false, error = "Location unavailable.")
                return@launch
            }

            getDailyForecastUseCase(location.latitude, location.longitude)
                .onEach { resource ->
                    when (resource) {
                        is Resource.Success -> state.copy(
                            forecastDaily = resource.data,
                            isLoading = false
                        )
                        is Resource.Loading -> state.copy(isLoading = true)
                        is Resource.Error -> state.copy(
                            isLoading = false,
                            error = resource.message ?: "An unknown error occurred."
                        )
                    }
                }
                .catch { exception ->
                    state.copy(
                        isLoading = false,
                        error = exception.message ?: "An unexpected error occurred."
                    )
                }
                .launchIn(this)
        }
    }

    companion object {
        const val TAG = "WeatherViewModel"
    }
}