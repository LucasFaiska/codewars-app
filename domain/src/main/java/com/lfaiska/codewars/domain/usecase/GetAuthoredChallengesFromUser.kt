package com.lfaiska.codewars.domain.usecase

import com.lfaiska.codewars.domain.entity.AuthoredChallenges

interface GetAuthoredChallengesFromUser {
    suspend fun execute(username: String, page: Int): AuthoredChallenges
}