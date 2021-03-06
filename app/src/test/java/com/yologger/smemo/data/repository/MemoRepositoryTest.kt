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
import org.mockito.ArgumentMatchers.anyLong
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

    @Test
    fun getMemoById() = runBlocking {
        // Given
        val dummyId = 1L
        val dummyTitle = "title1"
        val dummyContent = "content1"
        `when`(mockMemoDao.getById(1)).thenReturn(MemoEntity(1, dummyTitle,  dummyContent))

        // When
        val result = memoRepository.getMemoById(dummyId)

        // Then
        assertThat(result.title).isEqualTo(dummyTitle)
        assertThat(result.content).isEqualTo(dummyContent)
    }

    @Test
    fun updateMemo() = runBlocking {
        // Given
        `when`(mockMemoDao.update(MockitoHelper.anyObject())).thenReturn(1)
        `when`(mockMemoDao.getById(anyLong())).thenReturn(MemoEntity(1, "title2", "content2"))
        val memoDto = MemoDto(1, "title1", "content1")

        // When
        val result = memoRepository.updateMemo(memoDto)

        // Then
        assertThat(result.title).isEqualTo("title2")
        assertThat(result.content).isEqualTo("content2")
    }
}