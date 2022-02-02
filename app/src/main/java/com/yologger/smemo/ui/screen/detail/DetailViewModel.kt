package com.yologger.smemo.ui.screen.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yologger.smemo.data.repository.MemoRepository
import com.yologger.smemo.ui.dto.MemoDto
import com.yologger.smemo.ui.screen.create.CreateViewModel
import com.yologger.smemo.ui.util.SingleLiveEvent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class DetailViewModel(
    private val memoRepository: MemoRepository,
    private val coroutineDispatcher: CoroutineDispatcher
) : ViewModel() {

    val event: SingleLiveEvent<Event> = SingleLiveEvent()

    val liveId= MutableLiveData(0L)
    val liveTitle = MutableLiveData("")
    val liveContent = MutableLiveData("")

    fun setLiveData(memoDto: MemoDto) {
        liveId.value = memoDto.id
        liveTitle.value = memoDto.title
        liveContent.value = memoDto.content
    }

    fun onSave() = if (validateInputs()) updateMemo() else showErrorMessage()

    private fun validateInputs(): Boolean = !(liveTitle.value.isNullOrBlank() or liveContent.value.isNullOrBlank())

    private fun updateMemo() {
        val memoDto = MemoDto(id = liveId.value!!, title = liveTitle.value!!, content = liveContent.value!!)
        viewModelScope.launch(coroutineDispatcher) {
            val updated = memoRepository.updateMemo(memoDto)
            event.postValue(Event.ON_MEMO_UPDATED(updated))
        }
    }

    private fun showErrorMessage() {
        event.value = Event.INPUTS_EMPTY_ERROR
    }

    sealed class Event {
        data class ON_MEMO_UPDATED(val memoDto: MemoDto): Event()
        object INPUTS_EMPTY_ERROR: Event()
    }
}