package com.lfaiska.codewars.app.di

import com.lfaiska.codewars.app.presentation.scenes.authoredchallenges.list.AuthoredChallengesFragment
import com.lfaiska.codewars.app.presentation.scenes.completedchallenges.list.CompletedChallengesFragment
import com.lfaiska.codewars.app.presentation.scenes.users.UsersFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DomainModule::class, AppModule::class])
interface ApplicationComponent {
    fun inject(usersFragment: UsersFragment)
    fun inject(completedChallengesFragment: CompletedChallengesFragment)
    fun inject(authoredChallengesFragment: AuthoredChallengesFragment)
}