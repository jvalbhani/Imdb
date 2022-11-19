package com.test.imdb

import android.app.Application
import com.test.imdb.common.di.networkModule
import com.test.imdb.common.di.storageModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ImdbApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@ImdbApplication)
            modules(listOf(networkModule, storageModule)) // add home module
        }
    }
}