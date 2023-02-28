package com.example.repoapp

import android.app.Application
import com.example.repoapp.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class RepoApplication : Application() {

    companion object {
        lateinit var instance: RepoApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree());
        instance = this
        startKoin { androidContext(this@RepoApplication)
            modules(appModule)
        }
    }
}