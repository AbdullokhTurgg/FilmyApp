package com.example.data.cloud.provides

import retrofit2.Converter

interface ProvideConverterFactory {
    fun converterFactory(): Converter.Factory
}