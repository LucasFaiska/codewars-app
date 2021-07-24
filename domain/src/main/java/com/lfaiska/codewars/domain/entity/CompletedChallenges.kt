package com.lfaiska.codewars.domain.entity

data class CompletedChallenges (
    val totalPages: Int,
    val totalItems: Int,
    val data: List<CompletedChallenge>
)