package com.example.ecommerceconcept.data.api

import com.example.ecommerceconcept.utils.Constants
import com.example.ecommerceconcept.data.entities.MyCartDataItem
import com.example.ecommerceconcept.data.entities.ProductDetailsDataItem
import com.example.ecommerceconcept.data.entities.TestEntitiesItem
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiService {

    @Headers(
        "x-apikey: ${Constants.API_KEY_VALUE}",
        "accept: application/json",
    )
    @GET(Constants.API_MAIN_ENDPOINT)
    suspend fun getMain(): ArrayList<TestEntitiesItem>

    @Headers(
        "x-apikey: ${Constants.API_KEY_VALUE}",
        "accept: application/json",
    )
    @GET(Constants.API_MY_CART_ENDPOINT)
    suspend fun getCart(): ArrayList<MyCartDataItem>

    @Headers(
        "x-apikey: ${Constants.API_KEY_VALUE}",
        "accept: application/json",
    )
    @GET(Constants.API_PRODUCT_DETAILS_ENDPOINT)
    suspend fun getProductDetails(): ArrayList<ProductDetailsDataItem>

}