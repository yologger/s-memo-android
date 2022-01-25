package com.yologger.smemo.data.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.yologger.smemo.data.dao.MemoDao
import com.yologger.smemo.data.entity.MemoEntity
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AppDatabaseTest {

    private lateinit var db: AppDatabase
    private lateinit var dao: MemoDao

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        dao = db.memoDao()
    }

    @Test
    fun insertAndQuery() = runBlocking {
        val memoEntity = MemoEntity(id = 0, title = "title1", content = "content1")
        val id = dao.insert(memoEntity)
        val target = dao.getById(id)
        assertEquals("Both entities are equal", memoEntity.title, target.title)
    }

    @After
    fun tearDown() {
        db.close()
    }
}