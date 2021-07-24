package com.lfaiska.codewars.data.repository.mapper

import com.lfaiska.codewars.data.remote.dto.AuthoredChallengeResponse
import com.lfaiska.codewars.data.remote.dto.AuthoredChallengesResponse
import com.lfaiska.codewars.data.remote.dto.CompletedChallengeResponse
import com.lfaiska.codewars.data.remote.dto.CompletedChallengesResponse
import com.lfaiska.codewars.domain.entity.AuthoredChallenges
import com.lfaiska.codewars.domain.entity.Challenge
import com.lfaiska.codewars.domain.entity.CompletedChallenges

fun AuthoredChallengesResponse.toAuthoredChallenges(): AuthoredChallenges {
    return AuthoredChallenges(
        this.data.map { it.toChallenge() }
    )
}

fun AuthoredChallengeResponse.toChallenge(): Challenge {
    return Challenge(
        this.id,
        this.name
    )
}