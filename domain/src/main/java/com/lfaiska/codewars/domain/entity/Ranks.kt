package com.lfaiska.codewars.domain.entity

data class Ranks(
    val overall: Rank,
    val languages: Map<String, Rank>
)
