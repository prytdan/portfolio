package com.example.task2.kotlinfolder

import android.app.Application
import com.example.task2.BuildConfig
import com.example.task2.kotlinfolder.di.appModule
import com.example.task2.kotlinfolder.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@MyApplication)
            if (BuildConfig.DEBUG) {
                androidLogger(Level.ERROR)
            }
            modules(appModule)
            modules(repositoryModule)
        }
    }
}