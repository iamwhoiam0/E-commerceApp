package com.example.ecommerceconcept.data.repository

import com.example.ecommerceconcept.data.api.ApiHelper


class MainRepository(private val apiHelper: ApiHelper) {

    suspend fun getMain() = apiHelper.getMain()

    suspend fun getCart() = apiHelper.getCart()

    suspend fun getProductDetails() = apiHelper.getProductDetails()

}