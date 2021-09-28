package com.example.ecommerceconcept.main.data.api

import com.example.ecommerceconcept.main.data.entities.MainDataItem
import retrofit2.Response

class ApiHelperImpl (private val apiService: ApiService) : ApiHelper {

    override suspend fun getMain(): Response<ArrayList<MainDataItem>> = apiService.getMain()

}