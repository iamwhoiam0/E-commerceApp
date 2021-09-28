package com.example.ecommerceconcept.main.data.api

import com.example.ecommerceconcept.main.data.entities.MainDataItem
import com.example.ecommerceconcept.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiService {

    @Headers(
        "x-apikey: ${Constants.API_KEY_VALUE}",
        "accept: application/json",
    )
    @GET(Constants.API_MAIN_ENDPOINT)
    suspend fun getMain(): Response<ArrayList<MainDataItem>>

}