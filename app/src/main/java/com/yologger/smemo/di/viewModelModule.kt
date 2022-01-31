package com.yologger.smemo.di

import com.yologger.smemo.data.repository.MemoRepository
import com.yologger.smemo.ui.screen.create.CreateViewModel
import kotlinx.coroutines.CoroutineDispatcher
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel<CreateViewModel> { CreateViewModel(get<MemoRepository>(), get<CoroutineDispatcher>()) }
}