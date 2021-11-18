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

        val region = intent.getStringExtra("region")
//        val lat = intent.getStringExtra("lat")
//        val lon = intent.getStringExtra("lon")

        binding.weatherDetailsTitle.text = region
//
//        val imageId = intent.getIntExtra("imageId", R.drawable.img01)
//        val name = intent.getStringExtra("name")
//        val description = intent.getStringExtra("description")
//        val price = intent.getStringExtra("price")
//
//        binding.productDetailImage.setImageResource(imageId)
//        binding.productDetailName.text = name
//        binding.productDetailDescription.text = description
//        binding.productDetailPrice.text = price
    }
}