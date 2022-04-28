package com.example.nbateams.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class NBAApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@NBAApplication)
            modules(listOf(networkModule, nbaNetworkModule, networkPictureModule, domainModule, dataModule, presentationModule))
        }
    }
}