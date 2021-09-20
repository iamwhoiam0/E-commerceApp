package com.example.ecommerceconcept.data.api

class ApiHelper (private val apiService: ApiService){

    suspend fun getMain() = apiService.getMain()

    suspend fun getCart() = apiService.getCart()

    suspend fun getProductDetails() = apiService.getProductDetails()

}