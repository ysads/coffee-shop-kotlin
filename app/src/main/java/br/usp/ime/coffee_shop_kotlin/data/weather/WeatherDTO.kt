package br.usp.ime.coffee_shop_kotlin.data.weather

import br.usp.ime.coffee_shop_kotlin.domain.Weather

class WeatherDTO(var response: WeatherResponse) {
    private val weather by lazy { response.current.weather[0] }

    val temperature by lazy { response.current.temperature }
    val timestamp by lazy { response.current.timestamp }
    val feelsLike by lazy { response.current.feelsLike }
    val icon by lazy { weather.icon }
    val description by lazy { weather.description }

    fun asWeather(region: String?): Weather {
        return br.usp.ime.coffee_shop_kotlin.domain.Weather(
            temperature = temperature,
            feelsLike = feelsLike,
            timestamp = timestamp,
            icon = icon,
            description = description,
            region = region ?: "n/a",
        )
    }
}