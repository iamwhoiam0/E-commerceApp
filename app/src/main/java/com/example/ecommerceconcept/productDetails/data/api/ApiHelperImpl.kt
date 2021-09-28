package com.example.ecommerceconcept.productDetails.data.api

import com.example.ecommerceconcept.productDetails.data.entities.ProductDetailsDataItem
import retrofit2.Response

class ApiHelperImpl (private val apiService: ApiService) : ApiHelper {

    override suspend fun getProductDetails(): Response<ArrayList<ProductDetailsDataItem>> = apiService.getProductDetails()

}