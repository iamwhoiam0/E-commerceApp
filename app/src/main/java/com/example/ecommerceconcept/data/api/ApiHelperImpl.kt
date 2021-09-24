package com.example.ecommerceconcept.data.api

import com.example.ecommerceconcept.data.entities.MyCartDataItem
import com.example.ecommerceconcept.data.entities.ProductDetailsDataItem
import com.example.ecommerceconcept.data.entities.TestEntitiesItem
import retrofit2.Response

class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {

    override suspend fun getMain(): Response<ArrayList<TestEntitiesItem>> = apiService.getMain()

    override suspend fun getCart(): Response<ArrayList<MyCartDataItem>> = apiService.getCart()

    override suspend fun getProductDetails(): Response<ArrayList<ProductDetailsDataItem>> = apiService.getProductDetails()

}