package com.example.ecommerceconcept.productDetails.presentation.di.module

import com.example.ecommerceconcept.productDetails.presentation.viewModel.ProductDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val detailsViewModelModule = module {
    viewModel {
        ProductDetailsViewModel(get(), get())
    }
}