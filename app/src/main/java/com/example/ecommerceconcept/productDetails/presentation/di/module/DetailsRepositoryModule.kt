package com.example.ecommerceconcept.productDetails.presentation.di.module

import com.example.ecommerceconcept.productDetails.data.repository.ProductDetailsRepository
import org.koin.dsl.module

val detailsRepoModule = module {
    single {
        ProductDetailsRepository(get())
    }
}