package com.lfaiska.codewars.data.repository

import com.lfaiska.codewars.data.remote.ApiServices
import com.lfaiska.codewars.data.remote.core.ApiErrorResponse
import com.lfaiska.codewars.data.remote.core.ApiResponse
import com.lfaiska.codewars.data.remote.core.ApiSuccessResponse
import com.lfaiska.codewars.data.remote.core.error.RemoteErrorException
import com.lfaiska.codewars.data.remote.core.error.ResourceNotFoundException
import com.lfaiska.codewars.data.remote.dto.UserResponse
import com.lfaiska.codewars.data.repository.mapper.toUser
import com.lfaiska.codewars.domain.entity.User
import com.lfaiska.codewars.domain.error.DomainErrorException
import com.lfaiska.codewars.domain.error.ExecutionErrorException
import com.lfaiska.codewars.domain.error.UserNotFoundException
import com.lfaiska.codewars.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val apiServices: ApiServices
) : UserRepository {
    override suspend fun getUser(user: String): User {
        return when (val response = ApiResponse.create(apiServices.getUser(user))) {
            is ApiSuccessResponse<UserResponse> -> response.data.toUser()
            is ApiErrorResponse -> throw handleErrorResponse(response.error)
        }
    }

    private fun handleErrorResponse(error: RemoteErrorException) : DomainErrorException {
        return when (error) {
            is ResourceNotFoundException -> UserNotFoundException()
            else -> ExecutionErrorException()
        }
    }
}