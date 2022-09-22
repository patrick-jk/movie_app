package com.tokenlab.challenge.domain.di

import com.tokenlab.challenge.domain.usecase.GetDetailedMovieUseCase
import com.tokenlab.challenge.domain.usecase.GetMoviesUseCase
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

object DomainModule {
    fun load() = loadKoinModules(provideUseCaseModules())

    private fun provideUseCaseModules(): Module = module {
        factory { GetMoviesUseCase(get()) }
        factory { GetDetailedMovieUseCase(get()) }
    }
}