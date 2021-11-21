package br.usp.ime.coffee_shop_kotlin.data.products

import br.usp.ime.coffee_shop_kotlin.R
import br.usp.ime.coffee_shop_kotlin.domain.Product
import java.util.ArrayList
import kotlin.random.Random

val imageId = intArrayOf(
    R.drawable.img01, R.drawable.img02, R.drawable.img03,
    R.drawable.img04, R.drawable.img05, R.drawable.img06,
    R.drawable.img07, R.drawable.img08, R.drawable.img09,
    R.drawable.img10, R.drawable.img11, R.drawable.img12,
    R.drawable.img13
)

val coffeeTypes = listOf("Arábica", "Conilon", "Robusta", "Turco", "Indian", "Bourbon", "Catuaí")

val buzzwords = listOf("Microlote", "Orgânico", "Familiar", "Sustentável", "Bio")

val description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."

val locations = listOf(
    mapOf("region" to "Extrema", "lat" to "-22.845279715908166", "lon" to "-46.26128202417066"),
    mapOf("region" to "Mantiqueira", "lat" to "-21.712102139908595", "lon" to "-44.97262516495614"),
    mapOf("region" to "Caparaó", "lat" to "-20.52127663981693", "lon" to "-41.899352027340505"),
    mapOf("region" to "Chapada Diamantina", "lat" to "-11.383786785426516", "lon" to "-41.28926770023153"),
    mapOf("region" to "Wenceslau Braz", "lat" to "-23.87294633365956", "lon" to "-49.79904055892433")
)

class ProductsService {
    val LIMIT = imageId.size-1

    class Sample<T>(val coll: List<T>) {
         fun getSample(): T {
            val index = Random.nextInt(coll.size-1)
            return coll[index]
        }
    }

    fun getAll() : ArrayList<Product> {
        var products = ArrayList<Product>()

        for (i in 0..LIMIT) {
            val location = randLocation()
            val product = Product(
                imageId = imageId[i],
                name = randName(location["region"] ?: "n/a"),
                description = description,
                price = asCurrency(Random.nextInt(1000, 4000)),
                region = location["region"] ?: "n/a",
                lat = location["lat"] ?: "0",
                lon = location["lon"] ?: "0"
            )
            products.add(product)
        }

        return products
    }

    private fun asCurrency (value: Int) : String {
        return "£ ${value / 100.0}"
    }

    private fun randName (region: String): String {
        val type = Sample(coffeeTypes).getSample()
        val buzzword = Sample(buzzwords).getSample()

        return "$type $buzzword – $region"
    }

    private fun randLocation () : Map<String, String> {
        val index = Random.nextInt(locations.size)
        return locations[index]
    }
}