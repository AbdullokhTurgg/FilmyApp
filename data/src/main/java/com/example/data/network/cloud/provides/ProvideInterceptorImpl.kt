package com.example.data.network.cloud.provides

import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

abstract class ProvideInterceptorImpl(
    private val loggingLevel: HttpLoggingInterceptor.Level,
) : ProvideInterceptor {
    override fun loggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = loggingLevel
    }

    override fun requestInterceptor(): Interceptor = Interceptor { chain ->
        val request = chain.request().newBuilder()
            .cacheControl(CacheControl.Builder().maxAge(0, TimeUnit.SECONDS).build()).build()
        return@Interceptor chain.proceed(request)
    }

    class Debug : ProvideInterceptorImpl(loggingLevel = HttpLoggingInterceptor.Level.BODY)

    class Release : ProvideInterceptorImpl(loggingLevel = HttpLoggingInterceptor.Level.NONE)
}