package com.example.ecommerceconcept.productDetails.data.api

import com.example.ecommerceconcept.productDetails.data.entities.ProductDetailsDataItem
import com.example.ecommerceconcept.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiService {

    @Headers(
        "x-apikey: ${Constants.API_KEY_VALUE}",
        "accept: application/json",
    )
    @GET(Constants.API_PRODUCT_DETAILS_ENDPOINT)
    suspend fun getProductDetails(): Response<ArrayList<ProductDetailsDataItem>>

}