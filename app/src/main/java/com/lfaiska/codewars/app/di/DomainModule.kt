package com.lfaiska.codewars.app.di

import com.lfaiska.codewars.data.repository.di.RepositoryModule
import com.lfaiska.codewars.domain.repository.AuthoredChallengesRepository
import com.lfaiska.codewars.domain.repository.CompletedChallengesRepository
import com.lfaiska.codewars.domain.repository.UserRepository
import com.lfaiska.codewars.domain.usecase.*
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

    @Provides
    fun provideGetAuthoredChallengesFromUser(repository: AuthoredChallengesRepository): GetAuthoredChallengesFromUser {
        return GetAuthoredChallengesFromUserImpl(repository)
    }
}