package com.example.ecommerceconcept.main.data.entities

class MainData : ArrayList<MainDataItem>()

data class MainDataItem(
    val _id: String,
    val best_seller: List<BestSeller>,
    val home_store: List<HomeStore>
)

data class HomeStore(
    val id: Int,
    val is_buy: Boolean,
    val is_new: Boolean,
    val picture: String,
    val subtitle: String,
    val title: String
)

data class BestSeller(
    val discount_price: Int,
    val id: Int,
    val is_favorites: Boolean,
    val picture: String,
    val price_without_discount: Int,
    val title: String
)