package com.example.ecommerceconcept.presentation.di.module

import com.example.ecommerceconcept.presentation.viewModel.CartViewModel
import com.example.ecommerceconcept.presentation.viewModel.MainViewModel
import com.example.ecommerceconcept.presentation.viewModel.ProductViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
val viewModelModule = module {
    viewModel {
        MainViewModel(get(), get())
        ProductViewModel(get())
        CartViewModel(get())
    }
}