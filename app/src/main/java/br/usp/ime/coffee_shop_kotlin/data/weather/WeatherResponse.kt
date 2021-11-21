package br.usp.ime.coffee_shop_kotlin.data.weather

import com.google.gson.annotations.SerializedName

class WeatherResponse {
    @SerializedName("current")
    var current: Current = Current()
}

class Current {
    @SerializedName("dt")
    var timestamp: Int = 0

    @SerializedName("feels_like")
    var feelsLike: Float = 0.1F

    @SerializedName("temp")
    var temperature: Float = 0.1F

    @SerializedName("weather")
    var weather: ArrayList<Weather> = ArrayList()
}

class Weather {
    @SerializedName("description")
    var description: String = ""

    @SerializedName("icon")
    var icon : String = "04d"
}
