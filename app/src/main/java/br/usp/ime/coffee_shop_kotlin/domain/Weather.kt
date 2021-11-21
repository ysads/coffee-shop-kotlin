package br.usp.ime.coffee_shop_kotlin.domain

import br.usp.ime.coffee_shop_kotlin.R
import br.usp.ime.coffee_shop_kotlin.data.weather.WeatherResponse
import kotlin.math.roundToInt

class Weather(var response: WeatherResponse) {
    private val weather by lazy { response.current.weather[0] }

    fun temperature(): String {
        return "${response.current.temperature.roundToInt()}º"
    }

    fun feelsLike(): String {
        return "${response.current.feelsLike.roundToInt()}º"
    }

    fun condition(): String {
        return weather.description.capitalize()
    }

    fun weatherIcon(): Int {
        return when(weather.icon) {
            "01d", "01n" -> R.drawable.ic__01
            "02d", "02n" -> R.drawable.ic__02
            "03d", "03n" -> R.drawable.ic__03
            "04d", "04n" -> R.drawable.ic__04
            "09d", "09n" -> R.drawable.ic__09
            "10d", "10n" -> R.drawable.ic__10
            "11d", "11n" -> R.drawable.ic__11
            "13d", "13n" -> R.drawable.ic__13
            "50d", "50n" -> R.drawable.ic__50
            else -> R.drawable.ic__01
        }
    }
}