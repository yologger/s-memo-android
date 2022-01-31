package com.yologger.smemo.data.repository

import com.google.common.truth.Truth.assertThat
import com.yologger.smemo.common.MockitoHelper
import com.yologger.smemo.data.dao.MemoDao
import com.yologger.smemo.data.entity.MemoEntity
import com.yologger.smemo.ui.dto.MemoDto
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MemoRepositoryTest {

    @Mock
    lateinit var mockMemoDao: MemoDao

    @InjectMocks
    lateinit var memoRepository: MemoRepository

    @Before
    fun setUp() {

    }

    @After
    fun tearDown() {

    }

    @Test
    fun createMemo() = runBlocking {
        // Given
        val fakeId = 1L
        `when`(mockMemoDao.insert(MockitoHelper.anyObject())).thenReturn(fakeId)

        // When
        val memoDto = MemoDto(title = "title1", content = "content1")
        val resultId = memoRepository.createMemo(memoDto)

        // Then
        assertThat(resultId).isEqualTo(fakeId)
    }

    @Test
    fun getAllMemos() = runBlocking {

        // Given
        `when`(mockMemoDao.getAll()).thenReturn(listOf(
            MemoEntity(1, "title1",  "content1"),
            MemoEntity(2, "title2",  "content2"),
            MemoEntity(3, "title3",  "content3"),
            MemoEntity(4, "title4",  "content4"),
        ))

        // When
        val result = memoRepository.getAllMemos()

        // Then
        assertThat(result.size).isEqualTo(4)
    }
}