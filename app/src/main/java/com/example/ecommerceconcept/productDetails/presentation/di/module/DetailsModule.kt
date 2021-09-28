package com.example.ecommerceconcept.productDetails.presentation.di.module

import android.content.Context
import com.example.ecommerceconcept.productDetails.data.api.ApiHelper
import com.example.ecommerceconcept.productDetails.data.api.ApiHelperImpl
import com.example.ecommerceconcept.productDetails.data.api.ApiService
import com.example.ecommerceconcept.productDetails.data.repository.ProductDetailsRepository
import com.example.ecommerceconcept.productDetails.presentation.viewModel.ProductDetailsViewModel
import com.example.ecommerceconcept.utils.Constants
import com.example.ecommerceconcept.utils.NetworkHelper
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val detailsModule = module{

    factory { provideApiService(get()) }

    factory<ApiHelper> {
        return@factory ApiHelperImpl(get())
    }

    single {
        ProductDetailsRepository(get())
    }

    viewModel {
        ProductDetailsViewModel(get(), get())
    }
}

private fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)