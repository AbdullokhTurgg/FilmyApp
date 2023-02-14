package com.example.data.cloud.provides

import retrofit2.Retrofit

interface ProvideRetrofitBuilder {
    fun provideRetrofitBuilder(): Retrofit.Builder
}