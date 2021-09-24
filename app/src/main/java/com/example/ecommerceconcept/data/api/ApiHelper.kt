package com.example.ecommerceconcept.data.api

import com.example.ecommerceconcept.data.entities.MyCartDataItem
import com.example.ecommerceconcept.data.entities.ProductDetailsDataItem
import com.example.ecommerceconcept.data.entities.TestEntitiesItem
import retrofit2.Response

interface  ApiHelper{

    suspend fun getMain(): Response<ArrayList<TestEntitiesItem>>

    suspend fun getCart(): Response<ArrayList<MyCartDataItem>>

    suspend fun getProductDetails(): Response<ArrayList<ProductDetailsDataItem>>

}