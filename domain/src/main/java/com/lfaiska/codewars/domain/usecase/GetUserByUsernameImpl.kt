package com.lfaiska.codewars.domain.usecase

import com.lfaiska.codewars.domain.entity.User
import com.lfaiska.codewars.domain.error.UsernameEmptyException
import com.lfaiska.codewars.domain.repository.UserRepository

class GetUserByUsernameImpl(
    private val repository: UserRepository
) : GetUserByUsername {
    override suspend fun execute(username: String): User {
        if (username.isBlank()) throw UsernameEmptyException()
        return repository.getUser(username)
    }
}