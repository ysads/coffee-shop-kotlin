package br.usp.ime.coffee_shop_kotlin.domain

import java.io.Serializable

data class Product(
    var imageId: Int,
    var name: String,
    var description: String,
    var price: String,
    var region: String,
    var lat: String,
    var lon: String,
) : Serializable
