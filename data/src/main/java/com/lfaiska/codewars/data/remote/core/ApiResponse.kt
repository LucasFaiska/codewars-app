package com.lfaiska.codewars.data.remote.core

import com.lfaiska.codewars.data.remote.core.error.*
import retrofit2.Response
import java.net.HttpURLConnection
import javax.net.ssl.HttpsURLConnection

sealed class ApiResponse<T> {
    companion object {
        fun <T> create(response: Response<T>): ApiResponse<T> {
            return if (response.isSuccessful) {
                handleSuccessResponse(response)
            } else {
                handleErrorResponse(response)
            }
        }

        private fun <T> handleSuccessResponse(response: Response<T>): ApiResponse<T> {
            val body = response.body()
            return if (body == null || response.code() == HttpURLConnection.HTTP_NO_CONTENT) {
                ApiErrorResponse(EmptyResponseException())
            } else {
                ApiSuccessResponse(body)
            }
        }

        private fun <T> handleErrorResponse(response: Response<T>): ApiErrorResponse<T> {
            return when (response.code()) {
                HttpsURLConnection.HTTP_NOT_FOUND -> ApiErrorResponse(ResourceNotFoundException())
                HttpsURLConnection.HTTP_CLIENT_TIMEOUT -> ApiErrorResponse(TimeoutException())
                else -> ApiErrorResponse(UnhandledErrorException())
            }
        }
    }
}

class ApiSuccessResponse<T>(val data: T) : ApiResponse<T>()
class ApiErrorResponse<T>(val error: RemoteErrorException) : ApiResponse<T>()