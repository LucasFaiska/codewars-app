package com.lfaiska.codewars.data.remote

import com.lfaiska.codewars.data.remote.dto.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiServices {
    @GET("users/{username}")
    suspend fun getUser(@Path("username") username: String) : Response<UserResponse>
}