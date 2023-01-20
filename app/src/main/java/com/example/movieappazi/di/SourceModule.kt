package com.example.movieappazi.di

import com.example.data.network.cloud.handler.ResponseHandler
import com.example.data.network.cloud.handler.ResponseHandlerImpl
import com.example.domain.assistant.DispatchersProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
class SourceModule {

    @Provides
    fun provideResponseHandlerImpl(
        dispatchersProvider: DispatchersProvider,
    ): ResponseHandler = ResponseHandlerImpl(dispatchersProvider = dispatchersProvider)

    @Provides
    fun provideDispatchersProvider(): DispatchersProvider = DispatchersProvider.Base()
}