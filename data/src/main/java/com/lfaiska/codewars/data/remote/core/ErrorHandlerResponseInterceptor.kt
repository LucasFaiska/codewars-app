package com.lfaiska.codewars.data.remote.core

import com.lfaiska.codewars.data.remote.core.error.ResourceNotFoundException
import com.lfaiska.codewars.data.remote.core.error.TimeoutException
import com.lfaiska.codewars.data.remote.core.error.UnhandledErrorException
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.net.ssl.HttpsURLConnection

class ErrorHandlerResponseInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        val response = chain.proceed(request)

        if (response.isSuccessful) {
            return response
        } else {
            when(response.code()) {
                HttpsURLConnection.HTTP_NOT_FOUND -> throw ResourceNotFoundException()
                HttpsURLConnection.HTTP_CLIENT_TIMEOUT -> throw TimeoutException()
                else -> throw UnhandledErrorException()
            }
        }
    }
}