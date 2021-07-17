package com.lfaiska.codewars.domain.repository

import com.lfaiska.codewars.domain.entity.User

interface UserRepository {
    suspend fun getUser(username: String) : User
}