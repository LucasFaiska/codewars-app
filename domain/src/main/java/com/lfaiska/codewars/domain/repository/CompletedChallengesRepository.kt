package com.lfaiska.codewars.domain.repository

import com.lfaiska.codewars.domain.entity.CompletedChallenges

interface CompletedChallengesRepository {
    suspend fun getCompletedChallenges(user: String, page: Int) : CompletedChallenges
}
