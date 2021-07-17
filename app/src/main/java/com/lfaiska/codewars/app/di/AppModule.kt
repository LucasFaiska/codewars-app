package com.lfaiska.codewars.app.di

import android.content.Context
import com.lfaiska.codewars.app.MainApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {
    @Provides
    @Singleton
    fun providesContext(application: MainApplication): Context = application.applicationContext
}