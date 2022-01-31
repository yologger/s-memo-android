package com.yologger.smemo.ui.screen.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.orhanobut.logger.Logger
import com.yologger.smemo.data.repository.MemoRepository
import com.yologger.smemo.ui.dto.MemoDto
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class HomeViewModel(
    private val memoRepository: MemoRepository,
    private val coroutineDispatcher: CoroutineDispatcher
): ViewModel() {

    private val _memos = MutableLiveData<List<MemoDto>>(emptyList())
    val memos: LiveData<List<MemoDto>>
        get() = _memos

    init { fetch() }

    private fun fetch() {
        viewModelScope.launch(coroutineDispatcher) {
            val memoList = memoRepository.getAllMemos()
            _memos.postValue(memoList)
        }
    }
}