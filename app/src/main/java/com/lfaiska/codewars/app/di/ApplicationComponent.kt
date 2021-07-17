package com.lfaiska.codewars.app.di

import com.lfaiska.codewars.app.presentation.scenes.users.UsersFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DomainModule::class, AppModule::class])
interface ApplicationComponent {
    fun inject(usersFragment: UsersFragment)
}