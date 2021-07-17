package com.lfaiska.codewars.app.di

import com.lfaiska.codewars.data.repository.RepositoryModule
import com.lfaiska.codewars.domain.repository.UserRepository
import com.lfaiska.codewars.domain.usecase.GetUserByUsername
import com.lfaiska.codewars.domain.usecase.GetUserByUsernameImpl
import dagger.Module
import dagger.Provides

@Module(includes = [RepositoryModule::class])
class DomainModule {
    @Provides
    fun provideGetUserByUsername(repository: UserRepository) : GetUserByUsername {
        return GetUserByUsernameImpl(repository)
    }
}