package com.example.ecommerceconcept

import android.app.Application
import com.example.ecommerceconcept.main.presentation.di.module.*
import com.example.ecommerceconcept.productDetails.presentation.di.module.*
import com.example.ecommerceconcept.cart.presentation.di.module.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    mainModule, detailsModule, cartModule
                )
            )
        }
    }
}