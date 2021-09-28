package com.example.ecommerceconcept.main.presentation.di.module

import android.content.Context
import com.example.ecommerceconcept.main.data.api.ApiHelper
import com.example.ecommerceconcept.main.data.api.ApiHelperImpl
import com.example.ecommerceconcept.main.data.api.ApiService
import com.example.ecommerceconcept.main.data.repository.MainRepository
import com.example.ecommerceconcept.main.presentation.viewModel.MainViewModel
import com.example.ecommerceconcept.utils.Constants
import com.example.ecommerceconcept.utils.NetworkHelper
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val mainModule = module{

    single { provideRetrofit(Constants.BASE_URL) }
    factory { provideApiService(get()) }
    single { provideNetworkHelper(androidContext()) }

    factory<ApiHelper> {
        return@factory ApiHelperImpl(get())
    }

    single {
        MainRepository(get())
    }

    viewModel {
        MainViewModel(get(), get())
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