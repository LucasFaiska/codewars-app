package com.lfaiska.codewars.data.remote

import com.lfaiska.codewars.data.remote.dto.AuthoredChallengesResponse
import com.lfaiska.codewars.data.remote.dto.CompletedChallengesResponse
import com.lfaiska.codewars.data.remote.dto.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServices {
    @GET("users/{user}")
    suspend fun getUser(@Path("user") user: String): Response<UserResponse>

    @GET("users/{user}/code-challenges/completed")
    suspend fun getCompletedChallenges(@Path("user") user: String, @Query("page") page: Int) : Response<CompletedChallengesResponse>

    @GET("users/{user}/code-challenges/authored")
    suspend fun getAuthoredChallenges(@Path("user") user: String, @Query("page") page: Int) : Response<AuthoredChallengesResponse>
}