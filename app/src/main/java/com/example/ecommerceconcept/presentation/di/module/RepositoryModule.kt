package com.example.ecommerceconcept.presentation.di.module

import com.example.ecommerceconcept.data.repository.MainRepository
import org.koin.dsl.module

val repoModule = module {
    single {
        MainRepository(get())
    }
}