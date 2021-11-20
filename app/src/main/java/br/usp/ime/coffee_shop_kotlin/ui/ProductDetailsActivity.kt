package br.usp.ime.coffee_shop_kotlin.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.usp.ime.coffee_shop_kotlin.databinding.ActivityProductDetailsBinding
import br.usp.ime.coffee_shop_kotlin.domain.Product

class ProductDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductDetailsBinding
    private val product by lazy { intent.getSerializableExtra("product") as Product }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadDataToView()
        setupWeatherListener()
        setupMapsListener()
    }

    private fun loadDataToView() {
        binding.productDetailsImage.setImageResource(product.imageId)
        binding.productDetailsName.text = product.name
        binding.productDetailsDescription.text = product.description
        binding.productDetailsPrice.text = product.price
    }

    private fun setupWeatherListener() {
        binding.productDetailsSeeWeather.setOnClickListener {
            val intent = Intent(this, WeatherDetailsActivity::class.java)

            intent.putExtra("region", product.region)
            intent.putExtra("lat", product.lat)
            intent.putExtra("lon", product.lon)

            startActivity(intent)
        }
    }

    private fun setupMapsListener() {
        binding.productDetailsSeeMyLocation.setOnClickListener {
            val intent = Intent(this, MapsActivity::class.java)
            startActivity(intent)
        }
    }
}