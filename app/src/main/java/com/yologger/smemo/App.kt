package com.yologger.smemo

import android.app.Application
import com.yologger.smemo.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        setupKoin()
    }

    private fun setupKoin() {
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(listOf(
                appModule
            ))
        }
    }
}