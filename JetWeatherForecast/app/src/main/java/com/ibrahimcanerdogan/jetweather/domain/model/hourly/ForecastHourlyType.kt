package com.ibrahimcanerdogan.jetweather.domain.model.hourly

import androidx.annotation.DrawableRes
import com.ibrahimcanerdogan.jetweather.R

sealed class ForecastHourlyType(
    val weatherDesc: String,
    @DrawableRes val iconRes: Int
) {
    data object ClearSky : ForecastHourlyType(
        weatherDesc = "Clear sky",
        iconRes = R.drawable.ic_sunny
    )
    data object MainlyClear : ForecastHourlyType(
        weatherDesc = "Mainly clear",
        iconRes = R.drawable.ic_cloudy
    )
    data object PartlyCloudy : ForecastHourlyType(
        weatherDesc = "Partly cloudy",
        iconRes = R.drawable.ic_cloudy
    )
    data object Overcast : ForecastHourlyType(
        weatherDesc = "Overcast",
        iconRes = R.drawable.ic_cloudy
    )
    data object Foggy : ForecastHourlyType(
        weatherDesc = "Foggy",
        iconRes = R.drawable.ic_very_cloudy
    )
    data object DepositingRimeFog : ForecastHourlyType(
        weatherDesc = "Depositing rime fog",
        iconRes = R.drawable.ic_very_cloudy
    )
    data object LightDrizzle : ForecastHourlyType(
        weatherDesc = "Light drizzle",
        iconRes = R.drawable.ic_rainshower
    )
    data object ModerateDrizzle : ForecastHourlyType(
        weatherDesc = "Moderate drizzle",
        iconRes = R.drawable.ic_rainshower
    )
    data object DenseDrizzle : ForecastHourlyType(
        weatherDesc = "Dense drizzle",
        iconRes = R.drawable.ic_rainshower
    )
    data object LightFreezingDrizzle : ForecastHourlyType(
        weatherDesc = "Slight freezing drizzle",
        iconRes = R.drawable.ic_snowyrainy
    )
    data object DenseFreezingDrizzle : ForecastHourlyType(
        weatherDesc = "Dense freezing drizzle",
        iconRes = R.drawable.ic_snowyrainy
    )
    data object SlightRain : ForecastHourlyType(
        weatherDesc = "Slight rain",
        iconRes = R.drawable.ic_rainy
    )
    data object ModerateRain : ForecastHourlyType(
        weatherDesc = "Rainy",
        iconRes = R.drawable.ic_rainy
    )
    data object HeavyRain : ForecastHourlyType(
        weatherDesc = "Heavy rain",
        iconRes = R.drawable.ic_rainy
    )
    data object HeavyFreezingRain: ForecastHourlyType(
        weatherDesc = "Heavy freezing rain",
        iconRes = R.drawable.ic_snowyrainy
    )
    data object SlightSnowFall: ForecastHourlyType(
        weatherDesc = "Slight snow fall",
        iconRes = R.drawable.ic_snowy
    )
    data object ModerateSnowFall: ForecastHourlyType(
        weatherDesc = "Moderate snow fall",
        iconRes = R.drawable.ic_heavysnow
    )
    data object HeavySnowFall: ForecastHourlyType(
        weatherDesc = "Heavy snow fall",
        iconRes = R.drawable.ic_heavysnow
    )
    data object SnowGrains: ForecastHourlyType(
        weatherDesc = "Snow grains",
        iconRes = R.drawable.ic_heavysnow
    )
    data object SlightRainShowers: ForecastHourlyType(
        weatherDesc = "Slight rain showers",
        iconRes = R.drawable.ic_rainshower
    )
    data object ModerateRainShowers: ForecastHourlyType(
        weatherDesc = "Moderate rain showers",
        iconRes = R.drawable.ic_rainshower
    )
    data object ViolentRainShowers: ForecastHourlyType(
        weatherDesc = "Violent rain showers",
        iconRes = R.drawable.ic_rainshower
    )
    data object SlightSnowShowers: ForecastHourlyType(
        weatherDesc = "Light snow showers",
        iconRes = R.drawable.ic_snowy
    )
    data object HeavySnowShowers: ForecastHourlyType(
        weatherDesc = "Heavy snow showers",
        iconRes = R.drawable.ic_snowy
    )
    data object ModerateThunderstorm: ForecastHourlyType(
        weatherDesc = "Moderate thunderstorm",
        iconRes = R.drawable.ic_thunder
    )
    data object SlightHailThunderstorm: ForecastHourlyType(
        weatherDesc = "Thunderstorm with slight hail",
        iconRes = R.drawable.ic_rainythunder
    )
    data object HeavyHailThunderstorm: ForecastHourlyType(
        weatherDesc = "Thunderstorm with heavy hail",
        iconRes = R.drawable.ic_rainythunder
    )

    companion object {
        fun fromWMO(code: Int): ForecastHourlyType {
            return when(code) {
                0 -> ClearSky
                1 -> MainlyClear
                2 -> PartlyCloudy
                3 -> Overcast
                45 -> Foggy
                48 -> DepositingRimeFog
                51 -> LightDrizzle
                53 -> ModerateDrizzle
                55 -> DenseDrizzle
                56 -> LightFreezingDrizzle
                57 -> DenseFreezingDrizzle
                61 -> SlightRain
                63 -> ModerateRain
                65 -> HeavyRain
                66 -> LightFreezingDrizzle
                67 -> HeavyFreezingRain
                71 -> SlightSnowFall
                73 -> ModerateSnowFall
                75 -> HeavySnowFall
                77 -> SnowGrains
                80 -> SlightRainShowers
                81 -> ModerateRainShowers
                82 -> ViolentRainShowers
                85 -> SlightSnowShowers
                86 -> HeavySnowShowers
                95 -> ModerateThunderstorm
                96 -> SlightHailThunderstorm
                99 -> HeavyHailThunderstorm
                else -> ClearSky
            }
        }
    }
}