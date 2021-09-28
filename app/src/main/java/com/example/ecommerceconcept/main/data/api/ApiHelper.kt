package com.example.ecommerceconcept.main.data.api

import com.example.ecommerceconcept.main.data.entities.MainDataItem
import retrofit2.Response

interface ApiHelper {

    suspend fun getMain(): Response<ArrayList<MainDataItem>>

}