package com.example.movieappazi.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class IteractorModule {


//    @Provides
//    @Singleton
//    fun provideGetCategoriesUseCase(
//        repository: MovieRepositories,
//    ): GetCategoriesUseCase = GetCategoriesUseCase(repository = repository)
//
//    @Provides
//    @Singleton
//    fun provideGetCategoryDetailUseCase(
//        repository: MovieRepositories,
//    ): GetCategoryDetailUseCase = GetCategoryDetailUseCase(repository = repository)
}