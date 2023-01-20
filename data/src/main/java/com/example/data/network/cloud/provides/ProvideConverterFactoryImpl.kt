package com.example.data.network.cloud.provides

import com.example.data.network.cloud.provides.ProvideConverterFactory
import retrofit2.Converter
import retrofit2.converter.gson.GsonConverterFactory

class ProvideConverterFactoryImpl : ProvideConverterFactory {
    override fun converterFactory(): Converter.Factory = GsonConverterFactory.create()
}