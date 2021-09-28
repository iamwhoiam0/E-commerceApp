package com.example.ecommerceconcept.cart.presentation.di.module

import com.example.ecommerceconcept.cart.presentation.viewModel.CartViewModel
import com.example.ecommerceconcept.main.presentation.viewModel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
val cartViewModelModule = module {
    viewModel {
        CartViewModel(get(), get())
    }
}