package com.lfaiska.codewars.domain.usecase

import com.lfaiska.codewars.domain.entity.CompletedChallenges

interface GetCompletedChallengesFromUser {
    suspend fun execute(username: String, page: Int): CompletedChallenges
}