package com.lfaiska.codewars.domain.usecase

import com.lfaiska.codewars.domain.entity.User

interface GetUserByUsername {
    suspend fun execute(username: String) : User
}