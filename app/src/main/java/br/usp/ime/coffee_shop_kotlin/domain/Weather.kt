package br.usp.ime.coffee_shop_kotlin.domain

import br.usp.ime.coffee_shop_kotlin.R
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

class Weather(
    val temperature: Float,
    val timestamp: Int,
    val region: String,
    val feelsLike: Float? = null,
    val description: String? = null,
    val icon: String? = null
) {

    fun temperatureFormatted(): String {
        return "${temperature.roundToInt()}º"
    }

    fun feelsLikeFormatted(): String {
        return if (feelsLike != null) "${feelsLike.roundToInt()}º"  else ""
    }

    fun dateFormatted(): String {
        val formatter = SimpleDateFormat("dd/MM/yyyy")
        val netDate = Date(timestamp.toLong() * 1000)
        return formatter.format(netDate)
    }

    fun condition(): String {
        return description?.capitalize() ?: ""
    }

    fun weatherIcon(): Int {
        return when (icon) {
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