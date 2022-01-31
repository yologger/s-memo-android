package com.yologger.smemo.di

import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val coroutineDispatcherModule = module {
    factory { Dispatchers.IO }
}