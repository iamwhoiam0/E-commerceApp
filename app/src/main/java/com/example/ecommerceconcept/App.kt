package com.example.ecommerceconcept

import android.app.Application
import com.example.ecommerceconcept.presentation.di.module.appModule
import com.example.ecommerceconcept.presentation.di.module.repoModule
import com.example.ecommerceconcept.presentation.di.module.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(appModule, repoModule, viewModelModule))
        }
    }
}