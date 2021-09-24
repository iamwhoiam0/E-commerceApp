package com.example.ecommerceconcept.presentation.di.module

import android.content.Context
import com.example.ecommerceconcept.data.api.ApiHelper
import com.example.ecommerceconcept.data.api.ApiHelperImpl
import com.example.ecommerceconcept.data.api.ApiService
import com.example.ecommerceconcept.utils.Constants
import com.example.ecommerceconcept.utils.NetworkHelper
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module{
    single { provideRetrofit(Constants.BASE_URL) }
    factory { provideApiService(get()) }
    single { provideNetworkHelper(androidContext()) }

    single<ApiHelper> {
        return@single ApiHelperImpl(get())
    }
}

private fun provideNetworkHelper(context: Context) = NetworkHelper(context)

private fun provideRetrofit(BASE_URL: String): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

private fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)