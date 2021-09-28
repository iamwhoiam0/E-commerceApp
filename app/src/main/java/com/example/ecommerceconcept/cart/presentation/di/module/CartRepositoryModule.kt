package com.example.ecommerceconcept.cart.presentation.di.module

import com.example.ecommerceconcept.cart.data.repository.CartRepository
import org.koin.dsl.module

val cartRepoModule = module {
    single {
        CartRepository(get())
    }
}