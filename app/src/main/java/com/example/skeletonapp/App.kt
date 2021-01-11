package com.example.skeletonapp

import android.app.Application
import android.os.Build
import android.webkit.WebView
import com.example.skeletonapp.di.ApplicationComponent
import com.example.skeletonapp.di.DaggerApplicationComponent
import com.facebook.stetho.Stetho

class App : Application() {

    val appComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.factory().create(applicationContext)
    }

    override fun onCreate() {
        super.onCreate()

        // Stetho
        Stetho.initializeWithDefaults(this)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true)
        }
    }
}
