package com.example.movieappazi.app.di

import com.example.data.cloud.source.handler.ResponseHandler
import com.example.data.cloud.source.handler.ResponseHandlerImpl
import com.example.domain.helper.DispatchersProvider
import com.example.movieappazi.app.utils.exception.HandleExeption
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideResponseHandlerImpl(): ResponseHandler = ResponseHandlerImpl()

    @Provides
    fun provideDispatchersProvider(): DispatchersProvider = DispatchersProvider.Base()

    @Provides
    fun provideHandlerException(): HandleExeption = HandleExeption.Base()

}