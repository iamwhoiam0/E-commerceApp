package com.example.ecommerceconcept.main.data.repository

import com.example.ecommerceconcept.main.data.api.ApiHelper


class MainRepository (private val apiHelper: ApiHelper){

    suspend fun getMain() = apiHelper.getMain()

}