package br.usp.ime.coffee_shop_kotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.usp.ime.coffee_shop_kotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val imageId = intArrayOf(
            R.drawable.img01, R.drawable.img02, R.drawable.img03,
            R.drawable.img04, R.drawable.img05, R.drawable.img06,
            R.drawable.img07, R.drawable.img08, R.drawable.img09,
            R.drawable.img10, R.drawable.img11, R.drawable.img12,
            R.drawable.img13
        )

        val name = arrayOf(
            "Coffee 01", "Coffee 02", "Coffee 03",
            "Coffee 04", "Coffee 05", "Coffee 06",
            "Coffee 07", "Coffee 08", "Coffee 09",
            "Coffee 10", "Coffee 11", "Coffee 12",
            "Coffee 13"
        )

        val description = arrayOf(
            "Description 01", "Description 02", "Description 03",
            "Description 04", "Description 05", "Description 06",
            "Description 07", "Description 08", "Description 09",
            "Description 10", "Description 11", "Description 12",
            "Description 13"
        )

        val price = arrayOf(
            "$10.01", "$10.02", "$10.03",
            "$10.04", "$10.05", "$10.06",
            "$10.07", "$10.08", "$10.09",
            "$10.10", "$10.11", "$10.12",
            "$10.13"
        )

        val productsArrayList = ArrayList<Product>()

        for (i in name.indices) {
            val product = Product(imageId[i], name[i], description[i], price[i])
            productsArrayList.add(product)
        }

        binding.listview.isClickable = true
        binding.listview.adapter = Adapter(this, productsArrayList)
        binding.listview.setOnItemClickListener { _, _, position, _ ->
            val productImageId = imageId[position]
            val productName = name[position]
            val productDescription = description[position]
            val productPrice = price[position]

            val intent = Intent(this, ProductDetailActivity::class.java)
            intent.putExtra("imageId", productImageId)
            intent.putExtra("name", productName)
            intent.putExtra("description", productDescription)
            intent.putExtra("price", productPrice)
            startActivity(intent)
        }
    }
}
