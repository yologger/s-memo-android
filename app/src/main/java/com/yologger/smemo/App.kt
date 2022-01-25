package com.yologger.smemo

import android.app.Application
import com.yologger.smemo.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy

import com.orhanobut.logger.FormatStrategy
import org.koin.core.logger.Level


class App : Application() {
    override fun onCreate() {
        super.onCreate()
        setupKoin()
        setupLogger()
    }

    private fun setupKoin() {
        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@App)
            modules(
                listOf(
                    appModule
                )
            )
        }
    }

    private fun setupLogger() {
        val formatStrategy: FormatStrategy = PrettyFormatStrategy.newBuilder()
            // .showThreadInfo(false) // (Optional) Whether to show thread info or not. Default true
            // .methodCount(0) // (Optional) How many method line to show. Default 2
            // .methodOffset(7) // (Optional) Hides internal method calls up to offset. Default 5
            // .logStrategy(customLog) // (Optional) Changes the log strategy to print out. Default LogCat
            // .tag("PRETTY_LOGGER") // (Optional) Global tag for every log. Default PRETTY_LOGGER
            .build()

        Logger.addLogAdapter(object : AndroidLogAdapter(formatStrategy) {
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return BuildConfig.DEBUG
            }
        })
    }
}