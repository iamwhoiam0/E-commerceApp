package com.example.ecommerceconcept.main.presentation.di.module

import com.example.ecommerceconcept.main.data.repository.MainRepository
import org.koin.dsl.module

val mainRepoModule = module {
    single {
        MainRepository(get())
    }
}