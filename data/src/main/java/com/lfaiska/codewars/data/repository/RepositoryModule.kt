package com.lfaiska.codewars.data.repository

import com.lfaiska.codewars.data.remote.ApiServices
import com.lfaiska.codewars.domain.repository.UserRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {
    @Provides
    fun provideUserRepository(apiServices: ApiServices) : UserRepository {
        return UserRepositoryImpl(apiServices)
    }
}