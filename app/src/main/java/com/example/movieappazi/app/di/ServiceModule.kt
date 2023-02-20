package com.example.movieappazi.app.di

import com.example.data.cloud.api.api.movie.MovieApi
import com.example.data.cloud.api.api.person.PersonApi
import com.example.data.cloud.provides.MakeService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Provides
    fun provideMovieService(
        makeService: MakeService,
    ): MovieApi = makeService.service(MovieApi::class.java)

    @Provides
    fun providePersonService(
        makeService: MakeService,
    ): PersonApi = makeService.service(PersonApi::class.java)

}