package com.task.jobzella


import androidx.multidex.MultiDexApplication

import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


class MainApplication : MultiDexApplication(){
    override fun onCreate() {
        super.onCreate()

        startKoin {

            modules(com.task.jobzella.di.modules)
        }
    }
}