package com.example.instacat

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import timber.log.Timber

class CatsApplication : DaggerApplication() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().setApplication(this).build()
    }

    override fun onCreate() {
        super.onCreate()
        applicationInstance = this

        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }

    // This function is to be used when you want to get  an instance of application withing the app.
    companion object {
        private lateinit var applicationInstance: CatsApplication

        fun getInstance(): Application {
            return applicationInstance
        }
    }
}