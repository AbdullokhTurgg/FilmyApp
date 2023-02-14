package com.example.data.cloud.provides

import okhttp3.OkHttpClient

interface ProvideOkHttpClientBuilder {
    fun httpClientBuilder(): OkHttpClient
}