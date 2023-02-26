package com.example.movieappazi.app.di

import android.content.Context
import com.example.data.cloud.source.handler.ResponseHandler
import com.example.data.cloud.source.handler.ResponseHandlerImpl
import com.example.domain.helper.DispatchersProvider
import com.example.movieappazi.app.utils.exception.HandleExeption
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideResponseHandlerImpl(): ResponseHandler = ResponseHandlerImpl()

    @Provides
    fun provideDispatchersProvider(): DispatchersProvider = DispatchersProvider.Base()

    @Provides
    fun provideResourceProvider(@ApplicationContext context: Context): HandleExeption = HandleExeption.Base(context = context)

}