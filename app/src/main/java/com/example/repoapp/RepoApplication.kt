package com.example.repoapp

import android.app.Application
import com.example.repoapp.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin

class RepoApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin { androidContext(this@RepoApplication) }
        loadKoinModules(
            listOf(
                appModule
            )
        )
    }
}