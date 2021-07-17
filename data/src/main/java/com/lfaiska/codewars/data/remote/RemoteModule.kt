package com.lfaiska.codewars.data.remote

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.lfaiska.codewars.data.BuildConfig.BASE_URL
import com.lfaiska.codewars.data.remote.core.ErrorHandlerResponseInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class RemoteModule {
    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
            .addInterceptor(ErrorHandlerResponseInterceptor())
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
        return okHttpClientBuilder.build()
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder().setLenient().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create()
    }

    @Provides
    @Singleton
    fun provideRetrofitInstance(okHttpClient: OkHttpClient, gson: Gson): Retrofit =
        Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(BASE_URL)
            .build()

    @Provides
    @Singleton
    fun provideApiServices(retrofit: Retrofit): ApiServices = retrofit.create(ApiServices::class.java)

    companion object {
        private const val TIMEOUT = 10L
    }
}