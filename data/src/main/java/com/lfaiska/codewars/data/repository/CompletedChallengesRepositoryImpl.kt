package com.lfaiska.codewars.data.repository

import com.lfaiska.codewars.data.remote.ApiServices
import com.lfaiska.codewars.data.remote.core.ApiErrorResponse
import com.lfaiska.codewars.data.remote.core.ApiResponse
import com.lfaiska.codewars.data.remote.core.ApiSuccessResponse
import com.lfaiska.codewars.data.remote.core.error.RemoteErrorException
import com.lfaiska.codewars.data.remote.core.error.ResourceNotFoundException
import com.lfaiska.codewars.data.remote.dto.CompletedChallengesResponse
import com.lfaiska.codewars.data.repository.mapper.toCompletedChallenges
import com.lfaiska.codewars.domain.entity.CompletedChallenges
import com.lfaiska.codewars.domain.error.DomainErrorException
import com.lfaiska.codewars.domain.error.ExecutionErrorException
import com.lfaiska.codewars.domain.error.UserNotFoundException
import com.lfaiska.codewars.domain.repository.CompletedChallengesRepository
import javax.inject.Inject

class CompletedChallengesRepositoryImpl @Inject constructor(
    private val apiServices: ApiServices
) : CompletedChallengesRepository {
    override suspend fun getCompletedChallenges(username: String, page: Int): CompletedChallenges {
        return when (
            val response = ApiResponse.create(apiServices.getCompletedChallenges(username, page))
        ) {
            is ApiSuccessResponse<CompletedChallengesResponse> -> response.data.toCompletedChallenges()
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