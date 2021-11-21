package br.usp.ime.coffee_shop_kotlin.ui

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.LinearLayoutManager
import br.usp.ime.coffee_shop_kotlin.R
import br.usp.ime.coffee_shop_kotlin.adapters.WeatherListAdapter
import br.usp.ime.coffee_shop_kotlin.data.weather.WeatherDTO
import br.usp.ime.coffee_shop_kotlin.data.weather.WeatherResponse
import br.usp.ime.coffee_shop_kotlin.data.weather.WeatherService
import br.usp.ime.coffee_shop_kotlin.databinding.ActivityWeatherDetailsBinding
import br.usp.ime.coffee_shop_kotlin.db.WeatherDatabase
import br.usp.ime.coffee_shop_kotlin.domain.Weather
import br.usp.ime.coffee_shop_kotlin.utils.Logger
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

class WeatherDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWeatherDetailsBinding
    private lateinit var weatherDB : WeatherDatabase
    private lateinit var adapter : WeatherListAdapter

    private val region by lazy { intent.getStringExtra("region") }
    private val lat by lazy { intent.getStringExtra("lat") ?: "0" }
    private val lon by lazy { intent.getStringExtra("lon") ?: "0" }

    companion object {
        private const val APP_ID = "a63084d95f8b87d4922784d2399479ec"
        private const val BASE_URL = "https://api.openweathermap.org"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeatherDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initDatabase()
        loadDataFromDatabase()
        loadStaticData()
        fetchDataFromApi()
    }

    private fun initDatabase() {
        weatherDB = WeatherDatabase(this)
    }

    private fun loadDataFromDatabase() {
        val allWeather = weatherDB.getAll()

        binding.pastWeatherList.layoutManager = LinearLayoutManager(this)
        binding.pastWeatherList.adapter = WeatherListAdapter(this, allWeather)
    }

    private fun loadStaticData() {
        binding.loadingWrapper.visibility = View.VISIBLE
        binding.weatherWrapper.visibility = View.GONE
        binding.region.text = region
    }

    private fun fetchDataFromApi() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(WeatherService::class.java)
        val call = service.getCurrentWeather(lat, lon, APP_ID)

        call.enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(
                call: Call<WeatherResponse>,
                response: Response<WeatherResponse>
            ) {
                val data = response.body()

                if (data != null) {
                    val weather = WeatherDTO(data).asWeather(region)
                    loadDataToView(weather)
                    weatherDB.insert(weather)
                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                binding.loadingText.text = getString(R.string.weather_details_loading_failed)
            }
        })
    }

    private fun loadDataToView(weather: Weather) {
        binding.loadingWrapper.visibility = View.GONE
        binding.weatherWrapper.visibility = View.VISIBLE

        binding.temperature.text = weather.temperatureFormatted()
        binding.feelsLike.text = weather.feelsLikeFormatted()
        binding.condition.text = weather.condition()
        binding.icon.setImageDrawable(AppCompatResources.getDrawable(this, weather.weatherIcon()))
    }
}