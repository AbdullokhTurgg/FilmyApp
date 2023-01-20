package com.example.data.network.cloud.provides

import com.example.data.network.cloud.provides.ProvideInterceptor
import com.example.data.network.cloud.provides.ProvideOkHttpClientBuilder
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

class ProvideOkHttpClientBuilderImpl(
    private val provideInterceptor: ProvideInterceptor,
    private val timeOutSeconds: Long = 60L,
) : ProvideOkHttpClientBuilder {
    override fun httpClientBuilder(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(provideInterceptor.requestInterceptor())
            .addInterceptor(provideInterceptor.loggingInterceptor())
            .connectTimeout(timeOutSeconds, TimeUnit.SECONDS)
            .readTimeout(timeOutSeconds, TimeUnit.SECONDS)
            .build()
}