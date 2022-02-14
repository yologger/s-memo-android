package com.yologger.smemo.ui.screen.main.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.yologger.smemo.common.MainCoroutineRule
import com.yologger.smemo.common.MockitoHelper
import com.yologger.smemo.common.getOrAwaitValue
import com.yologger.smemo.data.repository.MemoRepository
import com.yologger.smemo.ui.dto.MemoDto
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyLong
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
            MemoDto(title = "title1", content = "content1"),
            MemoDto(title = "title2", content = "content2"),
            MemoDto(title = "title3", content = "content3"),
            MemoDto(title = "title4", content = "content4"),
            MemoDto(title = "title5", content = "content5"),
        ))

        val homeViewModel = HomeViewModel(fakeMemoRepository, TestCoroutineDispatcher())

        // When
        val fetched = homeViewModel.liveMemos.getOrAwaitValue()

        // Then
        assertThat(fetched.size).isEqualTo(5)
    }

    @Test
    fun addNewMemoById() = runBlockingTest {
        // Given
        val dummyId = 1L
        val dummyMemoDto = MemoDto(dummyId, "title1", "content1")
        Mockito.`when`(fakeMemoRepository.getMemoById(anyLong())).thenReturn(dummyMemoDto)
        val homeViewModel = HomeViewModel(fakeMemoRepository, TestCoroutineDispatcher())

        // When
        homeViewModel.addNewMemoById(dummyId)

        // Then
        assertThat(homeViewModel.event.getOrAwaitValue()).isEqualTo(HomeViewModel.Event.MEMO_ADDED(dummyMemoDto))
    }
}