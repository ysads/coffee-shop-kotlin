package br.usp.ime.coffee_shop_kotlin.db

import androidx.lifecycle.*
import kotlinx.coroutines.launch

class WeatherViewModel(private val weatherDao: WeatherDao) : ViewModel() {

    // Cache all items form the database using LiveData.
    val allItems: LiveData<List<Weather>> = weatherDao.getItems().asLiveData()

    /**
     * Inserts the new Weather into database.
     */
    fun addNewWeather(location: String, temperature: Double, timestamp: Int) {
        val newWeather = getNewWeatherEntry(location, temperature, timestamp)
        insertWeather(newWeather)
    }

    /**
     * Launching a new coroutine to insert an item in a non-blocking way
     */
    private fun insertWeather(weather: Weather) {
        viewModelScope.launch {
            weatherDao.insert(weather)
        }
    }

    /**
     * Returns an instance of the [Weather] entity class with the item info entered by the user.
     * This will be used to add a new entry to the database.
     */
    private fun getNewWeatherEntry(location: String, temperature: Double, timestamp: Int): Weather {
        return Weather(
            location = location,
            temperature = temperature,
            timestamp = timestamp
        )
    }

    /**
     * Factory class to instantiate the [ViewModel] class.
     */
    class WeatherViewModelFactory(private val weatherDao: WeatherDao) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(WeatherViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return WeatherViewModel(weatherDao) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}