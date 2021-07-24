package com.lfaiska.codewars.app.di

import com.lfaiska.codewars.data.repository.RepositoryModule
import com.lfaiska.codewars.domain.repository.CompletedChallengesRepository
import com.lfaiska.codewars.domain.repository.UserRepository
import com.lfaiska.codewars.domain.usecase.GetCompletedChallengesFromUser
import com.lfaiska.codewars.domain.usecase.GetCompletedChallengesFromUserImpl
import com.lfaiska.codewars.domain.usecase.GetUserByUsername
import com.lfaiska.codewars.domain.usecase.GetUserByUsernameImpl
import dagger.Module
import dagger.Provides

@Module(includes = [RepositoryModule::class])
class DomainModule {
    @Provides
    fun provideGetUserByUsername(repository: UserRepository): GetUserByUsername {
        return GetUserByUsernameImpl(repository)
    }

    @Provides
    fun provideGetCompletedChallengesFromUser(repository: CompletedChallengesRepository): GetCompletedChallengesFromUser {
        return GetCompletedChallengesFromUserImpl(repository)
    }
}