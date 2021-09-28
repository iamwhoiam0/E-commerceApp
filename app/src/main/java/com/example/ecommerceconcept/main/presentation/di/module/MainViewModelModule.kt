package com.example.ecommerceconcept.main.presentation.di.module

import com.example.ecommerceconcept.main.presentation.viewModel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
val mainViewModelModule = module {
    viewModel {
        MainViewModel(get(), get())
    }
}