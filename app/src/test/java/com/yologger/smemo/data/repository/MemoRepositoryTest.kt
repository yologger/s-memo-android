package com.yologger.smemo.data.repository

import com.google.common.truth.Truth.assertThat
import com.yologger.smemo.common.MockitoHelper
import com.yologger.smemo.data.dao.MemoDao
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
    fun test_createMemo() = runBlocking {

        // Given
        val fakeId = 1L
        `when`(mockMemoDao.insert(MockitoHelper.anyObject())).thenReturn(fakeId)

        // When
        val memoDto = MemoDto(title = "title1", content = "content1")
        val resultId = memoRepository.createMemo(memoDto)

        // Then
        assertThat(resultId).isEqualTo(fakeId)
    }
}