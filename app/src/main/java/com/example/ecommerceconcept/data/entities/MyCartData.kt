package com.example.ecommerceconcept.data.entities

class MyCartData : ArrayList<MyCartDataItem>()

data class MyCartDataItem(
    val Delivery: String,
    val _id: String,
    val basket: List<Basket>,
    val total: Int
)

data class Basket(
    val image: String,
    val price: Int,
    val title: String
)