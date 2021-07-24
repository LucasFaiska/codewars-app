package com.lfaiska.codewars.data.repository

import com.lfaiska.codewars.data.remote.ApiServices
import com.lfaiska.codewars.data.remote.core.ApiErrorResponse
import com.lfaiska.codewars.data.remote.core.ApiResponse
import com.lfaiska.codewars.data.remote.core.ApiSuccessResponse
import com.lfaiska.codewars.data.remote.core.error.RemoteErrorException
import com.lfaiska.codewars.data.remote.core.error.ResourceNotFoundException
import com.lfaiska.codewars.data.remote.dto.AuthoredChallengesResponse
import com.lfaiska.codewars.data.repository.mapper.toAuthoredChallenges
import com.lfaiska.codewars.domain.entity.AuthoredChallenges
import com.lfaiska.codewars.domain.error.DomainErrorException
import com.lfaiska.codewars.domain.error.ExecutionErrorException
import com.lfaiska.codewars.domain.error.UserNotFoundException
import com.lfaiska.codewars.domain.repository.AuthoredChallengesRepository
import javax.inject.Inject

class AuthoredChallengesRepositoryImpl @Inject constructor(
    private val apiServices: ApiServices
) : AuthoredChallengesRepository {
    override suspend fun getAuthoredChallenges(user: String, page: Int): AuthoredChallenges {
        return when (
            val response = ApiResponse.create(apiServices.getAuthoredChallenges(user, page))
        ) {
            is ApiSuccessResponse<AuthoredChallengesResponse> -> response.data.toAuthoredChallenges()
            is ApiErrorResponse -> throw handleErrorResponse(response.error)
        }
    }

    private fun handleErrorResponse(error: RemoteErrorException): DomainErrorException {
        return when (error) {
            is ResourceNotFoundException -> UserNotFoundException()
            else -> ExecutionErrorException()
        }
    }
}