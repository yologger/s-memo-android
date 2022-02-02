package com.yologger.smemo.ui.screen.create

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yologger.smemo.data.repository.MemoRepository
import com.yologger.smemo.ui.dto.MemoDto
import com.yologger.smemo.ui.util.SingleLiveEvent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CreateViewModel(
    private val memoRepository: MemoRepository,
    private val coroutineDispatcher: CoroutineDispatcher
) : ViewModel() {

    val event: SingleLiveEvent<Event> = SingleLiveEvent()

    val liveTitle = MutableLiveData("")
    val liveContent = MutableLiveData("")

    fun onSave() = if (validateInputs()) createMemo() else showErrorMessage()

    private fun validateInputs(): Boolean = !(liveTitle.value.isNullOrBlank() or liveContent.value.isNullOrBlank())

    private fun createMemo() {
        val memoDto = MemoDto(title = liveTitle.value!!, content = liveContent.value!!)
        viewModelScope.launch(coroutineDispatcher) {
            val id = memoRepository.createMemo(memoDto = memoDto)
            withContext(Dispatchers.Main) {
                event.value = Event.ON_MEMO_SAVED(id)
            }
        }
    }

    private fun showErrorMessage() {
        event.value = Event.INPUTS_EMPTY_ERROR
    }

    sealed class Event {
        data class ON_MEMO_SAVED(val id: Long): Event()
        object INPUTS_EMPTY_ERROR: Event()
    }
}