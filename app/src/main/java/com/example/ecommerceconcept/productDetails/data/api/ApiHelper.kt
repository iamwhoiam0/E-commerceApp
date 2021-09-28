package com.example.ecommerceconcept.productDetails.data.api

import com.example.ecommerceconcept.productDetails.data.entities.ProductDetailsDataItem
import retrofit2.Response

interface ApiHelper {

    suspend fun getProductDetails(): Response<ArrayList<ProductDetailsDataItem>>

}