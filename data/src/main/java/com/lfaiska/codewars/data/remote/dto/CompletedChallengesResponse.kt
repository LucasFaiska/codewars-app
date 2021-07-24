package com.lfaiska.codewars.data.remote.dto

data class CompletedChallengesResponse(
    val totalPages: Int,
    val totalItems: Int,
    val data: List<CompletedChallengeResponse>
)
