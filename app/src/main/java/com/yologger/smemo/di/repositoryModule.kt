package com.yologger.smemo.di

import com.yologger.smemo.data.repository.MemoRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<MemoRepository> { MemoRepository(get()) }
}