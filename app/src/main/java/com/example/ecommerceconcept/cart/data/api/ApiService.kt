package com.example.ecommerceconcept.cart.data.api

import com.example.ecommerceconcept.cart.data.entities.MyCartDataItem
import com.example.ecommerceconcept.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiService {

    @Headers(
        "x-apikey: ${Constants.API_KEY_VALUE}",
        "accept: application/json",
    )
    @GET(Constants.API_MY_CART_ENDPOINT)
    suspend fun getCart(): Response<ArrayList<MyCartDataItem>>

}