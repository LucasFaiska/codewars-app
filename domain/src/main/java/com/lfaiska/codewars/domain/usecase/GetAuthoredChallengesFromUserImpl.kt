package com.lfaiska.codewars.domain.usecase

import com.lfaiska.codewars.domain.entity.AuthoredChallenges
import com.lfaiska.codewars.domain.entity.CompletedChallenges
import com.lfaiska.codewars.domain.error.UsernameEmptyException
import com.lfaiska.codewars.domain.repository.AuthoredChallengesRepository
import com.lfaiska.codewars.domain.repository.CompletedChallengesRepository
import javax.inject.Inject

class GetAuthoredChallengesFromUserImpl @Inject constructor(
    private val repository: AuthoredChallengesRepository
) : GetAuthoredChallengesFromUser {
    override suspend fun execute(username: String, page: Int): AuthoredChallenges {
        if (username.isBlank()) throw UsernameEmptyException()
        return repository.getAuthoredChallenges(username, page)
    }
}