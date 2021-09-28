package com.example.ecommerceconcept.cart.presentation.di.module

import android.content.Context
import com.example.ecommerceconcept.cart.data.api.ApiHelper
import com.example.ecommerceconcept.cart.data.api.ApiHelperImpl
import com.example.ecommerceconcept.cart.data.api.ApiService
import com.example.ecommerceconcept.cart.data.repository.CartRepository
import com.example.ecommerceconcept.cart.presentation.viewModel.CartViewModel
import com.example.ecommerceconcept.utils.Constants
import com.example.ecommerceconcept.utils.NetworkHelper
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val cartModule = module{

    factory { provideApiService(get()) }

    factory<ApiHelper> {
        return@factory ApiHelperImpl(get())
    }

    single {
        CartRepository(get())
    }

    viewModel {
        CartViewModel(get(), get())
    }
}

private fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)