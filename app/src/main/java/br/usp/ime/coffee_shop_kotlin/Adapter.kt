package br.usp.ime.coffee_shop_kotlin

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class Adapter(private val context: Activity, private val arrayList: ArrayList<Product>) : ArrayAdapter<Product>(
    context, R.layout.list_item, arrayList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val inflater: LayoutInflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.list_item, null)

        val imageView: ImageView = view.findViewById(R.id.pic)
        val productName: TextView = view.findViewById(R.id.name)
        val productDescription: TextView = view.findViewById(R.id.description)
        val productPrice: TextView = view.findViewById(R.id.price)

        imageView.setImageResource(arrayList[position].imageId)
        productName.text = arrayList[position].name
        productDescription.text = arrayList[position].description
        productPrice.text = arrayList[position].price

        return view
    }
}