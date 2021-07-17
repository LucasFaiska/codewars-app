package com.lfaiska.codewars.data.repository.mapper

import com.lfaiska.codewars.data.remote.dto.RankResponse
import com.lfaiska.codewars.data.remote.dto.RanksResponse
import com.lfaiska.codewars.data.remote.dto.UserResponse
import com.lfaiska.codewars.domain.entity.Rank
import com.lfaiska.codewars.domain.entity.Ranks
import com.lfaiska.codewars.domain.entity.User

fun UserResponse.toUser(): User {
    return User(this.username, this.name, this.ranks.toRanks())
}

fun RanksResponse.toRanks(): Ranks {
    val languages = HashMap<String, Rank>()
    this.languages.map { languages.put(it.key, it.value.toRank()) }
    return Ranks(this.overall.toRank(), languages)
}

fun RankResponse.toRank(): Rank {
    return Rank(this.rank, this.name, this.score)
}