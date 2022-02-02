package com.yologger.smemo.ui.screen.create

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.yologger.smemo.common.MainCoroutineRule
import com.yologger.smemo.common.MockitoHelper
import com.yologger.smemo.common.getOrAwaitValue
import com.yologger.smemo.data.repository.MemoRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CreateViewModelTest {

    @Mock
    lateinit var fakeMemoRepository: MemoRepository

//    @InjectMocks
//    lateinit var createViewModel: CreateViewModel

    // Rule for livedata
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    // Rule for coroutine
    @get:Rule
    var coroutineRule = MainCoroutineRule()

    @Before
    fun setup() {
    }

    @After
    fun teardown() {

    }

    @Test
    @ExperimentalCoroutinesApi
    fun whenInvalidInputs()  {
        // Given
        val createViewModel = CreateViewModel(fakeMemoRepository, TestCoroutineDispatcher())
        createViewModel.liveTitle.value = ""
        createViewModel.liveContent.value = ""

        // When
        createViewModel.onSave()

        // Then
        assertThat(createViewModel.event.getOrAwaitValue()).isEqualTo(CreateViewModel.Event.INPUTS_EMPTY_ERROR)
    }

    @Test
    @ExperimentalCoroutinesApi
    fun whenValidInputs() = runBlockingTest {
        // Given
        val dummyMemoId = 1L
        `when`(fakeMemoRepository.createMemo(MockitoHelper.anyObject())).thenReturn(dummyMemoId)

        val createViewModel = CreateViewModel(fakeMemoRepository, TestCoroutineDispatcher())

        createViewModel.liveTitle.value = "dummy title"
        createViewModel.liveContent.value = "dummy test"

        // When
        createViewModel.onSave()

        // Then
        assertThat(createViewModel.event.getOrAwaitValue()).isEqualTo(CreateViewModel.Event.ON_MEMO_SAVED(dummyMemoId))
    }
}