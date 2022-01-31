package com.yologger.smemo.ui.screen.main.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.yologger.smemo.common.MainCoroutineRule
import com.yologger.smemo.common.getOrAwaitValue
import com.yologger.smemo.data.repository.MemoRepository
import com.yologger.smemo.ui.dto.MemoDto
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    @Mock
    lateinit var fakeMemoRepository: MemoRepository

    // Rule for livedata
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    // Rule for coroutine
    @get:Rule
    var coroutineRule = MainCoroutineRule()

    @Test
    fun fetchInitialData() = runBlockingTest {
        // Given
        Mockito.`when`(fakeMemoRepository.getAllMemos()).thenReturn(listOf(
            MemoDto("title1", "content1"),
            MemoDto("title2", "content2"),
            MemoDto("title3", "content3"),
            MemoDto("title4", "content4"),
            MemoDto("title5", "content5"),
        ))

        val homeViewModel = HomeViewModel(fakeMemoRepository, TestCoroutineDispatcher())

        // When
        val fetched = homeViewModel.memos.getOrAwaitValue()

        // Then
        assertThat(fetched.size).isEqualTo(5)
    }
}