package com.lfaiska.codewars.data.remote

import com.lfaiska.codewars.data.remote.dto.CompletedChallengesResponse
import com.lfaiska.codewars.data.remote.dto.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServices {
    @GET("users/{username}")
    suspend fun getUser(@Path("username") username: String): Response<UserResponse>

    @GET("users/{username}/code-challenges/completed")
    suspend fun getCompletedChallenges(@Path("username") username: String, @Query("page") page: Int) : Response<CompletedChallengesResponse>
}