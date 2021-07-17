package com.lfaiska.codewars.app

import android.app.Application
import com.lfaiska.codewars.app.di.ApplicationComponent
import com.lfaiska.codewars.app.di.DaggerApplicationComponent

class MainApplication : Application() {
    val appComponent: ApplicationComponent = DaggerApplicationComponent.create()

    override fun onCreate() {
        super.onCreate()
    }
}