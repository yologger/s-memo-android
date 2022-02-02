package com.yologger.smemo.ui.screen.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.orhanobut.logger.Logger
import com.yologger.smemo.data.repository.MemoRepository
import com.yologger.smemo.ui.dto.MemoDto
import com.yologger.smemo.ui.util.SingleLiveEvent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class HomeViewModel(
    private val memoRepository: MemoRepository,
    private val coroutineDispatcher: CoroutineDispatcher
) : ViewModel() {

    val event = SingleLiveEvent<Event>()

    private val _memos = mutableListOf<MemoDto>()

    private val _liveMemos = MutableLiveData<List<MemoDto>>(_memos)
    val liveMemos: LiveData<List<MemoDto>>
        get() = _liveMemos

    init {
        fetch()
    }

    private fun fetch() = viewModelScope.launch(coroutineDispatcher) {
        val memoList = memoRepository.getAllMemos()
        _liveMemos.postValue(memoList)
    }

    fun deleteAllMemos() = viewModelScope.launch(coroutineDispatcher) {
        memoRepository.deleteAllMemos()
        _liveMemos.postValue(emptyList())
    }

    fun addNewMemoById(id: Long) = viewModelScope.launch(coroutineDispatcher) {
        val memo = memoRepository.getMemoById(id)
        _memos.add(memo)
        event.postValue(Event.MEMO_ADDED(memo))
    }

    sealed class Event {
        data class MEMO_ADDED(val memoDto: MemoDto): Event()
    }
}