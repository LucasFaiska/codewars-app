package com.lfaiska.codewars.data.repository.mapper

import com.lfaiska.codewars.data.remote.dto.CompletedChallengeResponse
import com.lfaiska.codewars.data.remote.dto.CompletedChallengesResponse
import com.lfaiska.codewars.domain.entity.CompletedChallenge
import com.lfaiska.codewars.domain.entity.CompletedChallenges

fun CompletedChallengesResponse.toCompletedChallenges(): CompletedChallenges {
    return CompletedChallenges(
        this.totalPages,
        this.totalItems,
        this.data.map { it.toCompletedChallenge() })
}

fun CompletedChallengeResponse.toCompletedChallenge(): CompletedChallenge {
    return CompletedChallenge(
        this.id,
        this.name
    )
}