package br.usp.ime.coffee_shop_kotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.usp.ime.coffee_shop_kotlin.data.weather.WeatherResponse
import br.usp.ime.coffee_shop_kotlin.data.weather.WeatherService
import br.usp.ime.coffee_shop_kotlin.databinding.ActivityWeatherDetailsBinding
import br.usp.ime.coffee_shop_kotlin.utils.Logger
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWeatherDetailsBinding

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

        fetchDataFromApi()
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
                    loadDataToView(data)
                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                Logger.e("!" + t.toString())
                throw t
            }
        })
    }

    private fun loadDataToView(data: WeatherResponse) {
        binding.weatherDetailsTitle.text = region
        binding.weatherDetailsTemp.text = data?.current?.temperature.toString() ?: ""
        binding.weatherDetailsFeelsLike.text = data?.current?.feelsLike.toString() ?: ""
    }
}