package com.example.data.cloud.provides

import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor

interface ProvideInterceptor {
    fun loggingInterceptor(): HttpLoggingInterceptor

    fun requestInterceptor(): Interceptor
}