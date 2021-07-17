package com.lfaiska.codewars.data.remote

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.lfaiska.codewars.data.BuildConfig.BASE_URL
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class RemoteModule {

    @Provides
    fun provideRetrofitInstance(): Retrofit = Retrofit.Builder()
        .client(getHttpClient())
        .addConverterFactory(GsonConverterFactory.create(getGson()))
        .baseUrl(BASE_URL)
        .build()
}

fun getGson(): Gson {
    return GsonBuilder().setLenient().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create()
}

fun getHttpClient(): OkHttpClient {
    val okHttpClientBuilder = OkHttpClient.Builder()
    return okHttpClientBuilder.build()
}