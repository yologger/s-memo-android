package com.yologger.smemo.ui.screen.create

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yologger.smemo.data.repository.MemoRepository
import com.yologger.smemo.ui.dto.MemoDto
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CreateViewModel(
    private val memoRepository: MemoRepository,
    private val coroutineDispatcher: CoroutineDispatcher
) : ViewModel() {

    var state: MutableLiveData<State> = MutableLiveData(State.IDLE)

    val liveTitle = MutableLiveData("")
    val liveContent = MutableLiveData("")

    fun onSave() = if (validateInputs()) createMemo() else showErrorMessage()

    private fun validateInputs(): Boolean = !(liveTitle.value.isNullOrBlank() or liveContent.value.isNullOrBlank())

    private fun createMemo() {
        val memoDto = MemoDto(title = liveTitle.value!!, content = liveContent.value!!)
        viewModelScope.launch(coroutineDispatcher) {
            memoRepository.createMemo(memoDto = memoDto)
            withContext(Dispatchers.Main) {
                state.value = State.ON_MEMO_SAVED
            }
        }
    }

    private fun showErrorMessage() {
        state.value = State.INPUTS_EMPTY_ERROR
    }

    enum class State {
        IDLE,
        ON_MEMO_SAVED,
        INPUTS_EMPTY_ERROR
    }
}