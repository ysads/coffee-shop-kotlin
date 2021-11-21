package br.usp.ime.coffee_shop_kotlin.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather")
data class Weather(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "location")
    val location: String,
    @ColumnInfo(name = "temperature")
    val temperature: Double,
    @ColumnInfo(name = "timestamp")
    val timestamp: Int
) {
}