package br.usp.ime.coffee_shop_kotlin.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.usp.ime.coffee_shop_kotlin.adapters.ProductListAdapter
import br.usp.ime.coffee_shop_kotlin.data.products.ProductsService
import br.usp.ime.coffee_shop_kotlin.databinding.ActivityMainBinding
import br.usp.ime.coffee_shop_kotlin.domain.Product
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var products: ArrayList<Product>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadUser()
        loadProductsIntoList()
    }

    private fun loadUser() {
        val picURL = intent.getStringExtra("picURL")

        binding.name.text =
            intent.getStringExtra("firstName") + " " + intent.getStringExtra("lastName")
        Picasso.get()
            .load(picURL)
            .into(binding.pic)
    }

    private fun loadProductsIntoList() {
        products = ProductsService().getAll()

        binding.listview.isClickable = true
        binding.listview.adapter = ProductListAdapter(this, products)
        binding.listview.setOnItemClickListener { _, _, position, _ ->
            val intent = Intent(this, ProductDetailsActivity::class.java)
            intent.putExtra("product", products[position])
            startActivity(intent)
        }
    }
}
