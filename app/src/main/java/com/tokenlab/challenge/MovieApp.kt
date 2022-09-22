package com.tokenlab.challenge

import android.app.Application
import com.tokenlab.challenge.data.di.DataModule
import com.tokenlab.challenge.domain.di.DomainModule
import com.tokenlab.challenge.presentation.di.PresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MovieApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MovieApp)
        }

        DataModule.load()
        DomainModule.load()
        PresentationModule.load()
    }
}