package br.usp.ime.coffee_shop_kotlin.db

import android.app.Application

class WeatherApplication : Application() {
    // Using by lazy so the database is only created when needed
    // rather than when the application starts
    val database: WeatherRoomDatabase by lazy { WeatherRoomDatabase.getDatabase(this) }
}