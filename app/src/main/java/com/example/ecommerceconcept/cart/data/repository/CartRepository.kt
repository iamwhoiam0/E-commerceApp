package com.example.ecommerceconcept.cart.data.repository

import com.example.ecommerceconcept.cart.data.api.ApiHelper

class CartRepository(private val apiHelper: ApiHelper) {

    suspend fun getCart() = apiHelper.getCart()

}