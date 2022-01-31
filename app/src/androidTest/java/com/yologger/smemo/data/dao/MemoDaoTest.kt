package com.yologger.smemo.data.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.yologger.smemo.data.database.AppDatabase
import com.yologger.smemo.data.entity.MemoEntity
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MemoDaoTest {
    private lateinit var db: AppDatabase
    private lateinit var dao: MemoDao

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        // Use In-memory database
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        dao = db.memoDao()
    }

    @Test
    fun insert_getAll() = runBlocking {
        // Given
        val memoEntity = MemoEntity(id = 0, title = "title1", content = "content1")

        // When
        val id = dao.insert(memoEntity)
        val newMemoEntity = dao.getById(id)

        // Then
        assertThat(newMemoEntity.title).isEqualTo(memoEntity.title)
    }

    @Test
    fun queryAll() = runBlocking {
        // Given
        dao.insert(MemoEntity(title = "title1", content = "content1"))
        dao.insert(MemoEntity(title = "title2", content = "content2"))
        dao.insert(MemoEntity(title = "title3", content = "content3"))
        dao.insert(MemoEntity(title = "title4", content = "content4"))

        // When
        val memoList = dao.getAll()

        // Then
        assertThat(memoList.size).isEqualTo(4)
    }

    @After
    fun tearDown() {
        db.close()
    }
}