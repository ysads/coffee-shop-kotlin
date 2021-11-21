package br.usp.ime.coffee_shop_kotlin.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import br.usp.ime.coffee_shop_kotlin.adapters.ProductListAdapter
import br.usp.ime.coffee_shop_kotlin.data.products.ProductsService
import br.usp.ime.coffee_shop_kotlin.databinding.ActivityMainBinding
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val firstName: TextView = binding.firstName
        firstName.text = intent.getStringExtra("firstName")

        val lastName: TextView = binding.lastName
        lastName.text = intent.getStringExtra("lastName")

        val pic: ImageView = binding.pic
        val picURL = intent.getStringExtra("picURL")
        Picasso.get()
            .load(picURL)
            .into(pic)

        Log.i("First Name", firstName.toString())
        Log.i("Last Name", lastName.toString())
        Log.i("Pic URL", picURL.toString())

        val productsArrayList = ProductsService().getAll()

        binding.listview.isClickable = true
        binding.listview.adapter = ProductListAdapter(this, productsArrayList)
        binding.listview.setOnItemClickListener { _, _, position, _ ->
            val intent = Intent(this, ProductDetailsActivity::class.java)
            intent.putExtra("product", productsArrayList[position])
            startActivity(intent)
        }
    }
}
