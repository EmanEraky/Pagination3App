package com.eman.paginationapp

import android.app.Application
import com.eman.paginationapp.di.module.appModule
import com.eman.paginationapp.di.module.repoModule
import com.eman.paginationapp.di.module.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(appModule, repoModule, viewModelModule))
        }
    }
}

