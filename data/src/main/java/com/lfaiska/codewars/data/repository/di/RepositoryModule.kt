package com.lfaiska.codewars.data.repository.di

import com.lfaiska.codewars.data.remote.ApiServices
import com.lfaiska.codewars.data.remote.RemoteModule
import com.lfaiska.codewars.data.repository.AuthoredChallengesRepositoryImpl
import com.lfaiska.codewars.data.repository.CompletedChallengesRepositoryImpl
import com.lfaiska.codewars.data.repository.UserRepositoryImpl
import com.lfaiska.codewars.domain.repository.AuthoredChallengesRepository
import com.lfaiska.codewars.domain.repository.CompletedChallengesRepository
import com.lfaiska.codewars.domain.repository.UserRepository
import dagger.Module
import dagger.Provides

@Module(includes = [RemoteModule::class])
class RepositoryModule {
    @Provides
    fun provideUserRepository(apiServices: ApiServices): UserRepository {
        return UserRepositoryImpl(apiServices)
    }

    @Provides
    fun provideCompletedChallengesRepository(apiServices: ApiServices): CompletedChallengesRepository {
        return CompletedChallengesRepositoryImpl(apiServices)
    }

    @Provides
    fun provideAuthoredChallengesRepository(apiServices: ApiServices): AuthoredChallengesRepository {
        return AuthoredChallengesRepositoryImpl(apiServices)
    }
}