package com.lfaiska.codewars.data.remote.dto

data class AuthoredChallengeResponse (
    val id: String,
    val name: String,
    val description: String,
    val rank: Int,
    val rankName: String,
    val tags: List<String>,
    val languages: List<String>,
)
