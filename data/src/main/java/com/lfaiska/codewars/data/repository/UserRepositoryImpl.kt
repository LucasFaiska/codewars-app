package com.lfaiska.codewars.data.repository

import com.lfaiska.codewars.data.remote.ApiServices
import com.lfaiska.codewars.data.remote.core.error.ResourceNotFoundException
import com.lfaiska.codewars.data.remote.core.error.UnhandledErrorException
import com.lfaiska.codewars.data.repository.mapper.toUser
import com.lfaiska.codewars.domain.entity.User
import com.lfaiska.codewars.domain.error.ExecutionErrorException
import com.lfaiska.codewars.domain.error.UserNotFoundException
import com.lfaiska.codewars.domain.repository.UserRepository

class UserRepositoryImpl(
    private val apiServices: ApiServices
) : UserRepository {
    override suspend fun getUser(username: String): User {
        return try {
            apiServices.getUser(username).toUser()
        } catch (resourceNotFoundException: ResourceNotFoundException) {
            throw UserNotFoundException()
        } catch (unhandledErrorException: UnhandledErrorException) {
            throw ExecutionErrorException()
        }
    }
}