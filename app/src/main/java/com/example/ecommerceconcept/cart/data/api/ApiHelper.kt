package com.example.ecommerceconcept.cart.data.api

import com.example.ecommerceconcept.cart.data.entities.MyCartDataItem
import retrofit2.Response

interface ApiHelper {

    suspend fun getCart(): Response<ArrayList<MyCartDataItem>>

}