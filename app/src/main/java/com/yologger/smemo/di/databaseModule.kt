package com.yologger.smemo.di

import androidx.room.Room
import com.yologger.smemo.data.database.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {

    // Register AppDatabase
    single {
        Room.databaseBuilder(androidContext(), AppDatabase::class.java, "memo_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    // Register Dao
    single { get<AppDatabase>().memoDao() }
}