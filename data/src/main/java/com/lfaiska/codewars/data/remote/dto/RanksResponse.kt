package com.lfaiska.codewars.data.remote.dto

data class RanksResponse(
    val overall: RankResponse,
    val languages: Map<String, RankResponse>
)