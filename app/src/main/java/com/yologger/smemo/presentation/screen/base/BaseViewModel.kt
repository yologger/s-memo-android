package com.yologger.smemo.presentation.screen.base

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable

open class BaseViewModel: ViewModel() {

    val disposables by lazy { CompositeDisposable() }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}