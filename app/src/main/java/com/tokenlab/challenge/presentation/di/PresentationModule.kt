package com.tokenlab.challenge.presentation.di

import com.tokenlab.challenge.presentation.detail.DetailViewModel
import com.tokenlab.challenge.presentation.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

object PresentationModule {
    fun load() = loadKoinModules(provideViewModelModules())

    private fun provideViewModelModules(): Module {
        return module {
            viewModel { MainViewModel(get()) }
            viewModel { DetailViewModel(get()) }
        }
    }
}