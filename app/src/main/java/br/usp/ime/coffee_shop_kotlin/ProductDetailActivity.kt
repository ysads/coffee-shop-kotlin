package br.usp.ime.coffee_shop_kotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.usp.ime.coffee_shop_kotlin.databinding.ActivityProductDetailBinding

class ProductDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductDetailBinding
    private val product by lazy { intent.getSerializableExtra("product") as Product }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadDataToView()
        setupWeatherListener()
    }

    private fun loadDataToView() {
        binding.productDetailImage.setImageResource(product.imageId)
        binding.productDetailName.text = product.name
        binding.productDetailDescription.text = product.description
        binding.productDetailPrice.text = product.price
    }

    private fun setupWeatherListener() {
        binding.productDetailsSeeWeather.setOnClickListener {
            val intent = Intent(this, WeatherDetailsActivity::class.java)
            intent.putExtra("region", product.region)
            startActivity(intent)
        }
    }
}