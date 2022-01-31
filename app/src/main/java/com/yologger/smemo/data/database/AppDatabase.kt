package com.yologger.smemo.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.yologger.smemo.data.dao.MemoDao
import com.yologger.smemo.data.entity.MemoEntity

@Database(entities = [MemoEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun memoDao(): MemoDao
}