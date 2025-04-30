package com.app.zerobrokagepackersandmovers.RetrofitInstance

import com.app.zerobrokagepackersandmovers.Interface.OlamapsApi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val baseUrl = "https://api.olamaps.io/"

    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val originInterceptor = Interceptor { chain ->
        val originalRequest = chain.request()
        val newRequest = originalRequest.newBuilder()
            .addHeader("X-Request-Id", "123456")
            .addHeader("Origin", "https://tax.zerobrokage.com")
            .build()
        chain.proceed(newRequest)
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(originInterceptor)
        .addInterceptor(logging)
        .build()

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    val api: OlamapsApi by lazy {
        retrofit.create(OlamapsApi::class.java)
    }
}
