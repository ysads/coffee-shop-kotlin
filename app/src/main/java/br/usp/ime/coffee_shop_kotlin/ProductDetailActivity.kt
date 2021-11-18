package br.usp.ime.coffee_shop_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.usp.ime.coffee_shop_kotlin.databinding.ActivityProductDetailBinding

class ProductDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val imageId = intent.getIntExtra("imageId", R.drawable.img01)
        val name = intent.getStringExtra("name")
        val description = intent.getStringExtra("description")
        val price = intent.getStringExtra("price")

        binding.productDetailImage.setImageResource(imageId)
        binding.productDetailName.text = name
        binding.productDetailDescription.text = description
        binding.productDetailPrice.text = price
    }
}