package br.usp.ime.coffee_shop_kotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.usp.ime.coffee_shop_kotlin.databinding.ActivityWeatherDetailsBinding

class WeatherDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWeatherDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeatherDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadDataToView()
    }

    private fun loadDataToView() {
        val region = intent.getStringExtra("region")
        binding.weatherDetailsTitle.text = region
    }
}