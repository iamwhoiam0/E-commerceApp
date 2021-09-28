package com.example.ecommerceconcept.productDetails.data.repository

import com.example.ecommerceconcept.productDetails.data.api.ApiHelper

class ProductDetailsRepository (private val apiHelper: ApiHelper){

    suspend fun getProductDetails() = apiHelper.getProductDetails()

}