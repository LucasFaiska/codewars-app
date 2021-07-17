package com.lfaiska.codewars.data.repository

import com.lfaiska.codewars.data.remote.ApiServices
import com.lfaiska.codewars.data.remote.RemoteModule
import com.lfaiska.codewars.domain.repository.UserRepository
import dagger.Module
import dagger.Provides

@Module(includes = [RemoteModule::class])
class RepositoryModule {
    @Provides
    fun provideUserRepository(apiServices: ApiServices): UserRepository {
        return UserRepositoryImpl(apiServices)
    }
}