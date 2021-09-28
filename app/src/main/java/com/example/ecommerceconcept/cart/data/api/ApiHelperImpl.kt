package com.example.ecommerceconcept.cart.data.api

import com.example.ecommerceconcept.cart.data.entities.MyCartDataItem
import retrofit2.Response

class ApiHelperImpl (private val apiService: ApiService) : ApiHelper {

    override suspend fun getCart(): Response<ArrayList<MyCartDataItem>> = apiService.getCart()

}