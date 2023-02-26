package com.example.movieappazi.app.di

import com.example.data.cloud.provides.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    fun provideMakeService(
        retrofitBuilder: ProvideRetrofitBuilder,
    ): MakeService = MakeServiceImpl(retrofitBuilder = retrofitBuilder)


    @Provides
    fun provideProvideConverterFactory(): ProvideConverterFactory = ProvideConverterFactoryImpl()

    @Provides
    fun provideProvideInterceptorDebug(): ProvideInterceptor = ProvideInterceptorImpl.Debug()

    @Provides
    fun provideProvideOkHttpClientBuilder(
        provideInterceptor: ProvideInterceptor,
    ): ProvideOkHttpClientBuilder =
        ProvideOkHttpClientBuilderImpl(provideInterceptor = provideInterceptor)

    @Provides
    fun provideProvideRetrofitBuilder(
        provideConverterFactory: ProvideConverterFactory,
        provideOkHttpClientBuilder: ProvideOkHttpClientBuilder,
    ): ProvideRetrofitBuilder = ProvideRetrofitBuilderImpl(
        provideConverterFactory = provideConverterFactory,
        provideOkHttpClientBuilder = provideOkHttpClientBuilder
    )

}