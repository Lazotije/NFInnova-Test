package com.example.repoapp.utils

import com.example.repoapp.network.BASE_URL
import com.example.repoapp.utils.HttpClientManager.defaultHttpClient
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val HTTP_TIMEOUT = 60L

inline fun <reified T : Any>getRetrofitService(): T {
    val retrofit = Retrofit.Builder()
        .client(defaultHttpClient)
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    return retrofit.create(T::class.java)
}

object HttpClientManager {
    val defaultHttpClient : OkHttpClient = OkHttpClient().newBuilder()
        .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
        .connectTimeout(HTTP_TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(HTTP_TIMEOUT, TimeUnit.SECONDS)
        .writeTimeout(HTTP_TIMEOUT, TimeUnit.SECONDS).build()
}