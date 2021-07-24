package com.lfaiska.codewars.domain.usecase

import com.lfaiska.codewars.domain.entity.CompletedChallenges
import com.lfaiska.codewars.domain.error.UsernameEmptyException
import com.lfaiska.codewars.domain.repository.CompletedChallengesRepository
import javax.inject.Inject

class GetCompletedChallengesFromUserImpl @Inject constructor(
    private val repository: CompletedChallengesRepository
) : GetCompletedChallengesFromUser {
    override suspend fun execute(username: String, page: Int): CompletedChallenges {
        if (username.isBlank()) throw UsernameEmptyException()
        return repository.getCompletedChallenges(username, page)
    }
}