package com.lfaiska.codewars.domain.repository

import com.lfaiska.codewars.domain.entity.AuthoredChallenges

interface AuthoredChallengesRepository {
    suspend fun getAuthoredChallenges(user: String, page: Int): AuthoredChallenges
}
