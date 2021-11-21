package br.usp.ime.coffee_shop_kotlin.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(weather: Weather)

    @Query("SELECT * from weather")
    fun getItems(): Flow<List<Weather>>
}