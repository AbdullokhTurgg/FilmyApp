package com.example.movieappazi.app.di

import com.example.data.cloud.source.movie.CloudDataSourceMovie
import com.example.data.cloud.source.movie.CloudDataSourceMovieImpl
import com.example.data.cloud.source.person.CloudDataSourcePerson
import com.example.data.cloud.source.person.CloudDataSourcePersonImpl
import com.example.data.data.domainRepositoryImpl.network.movie.MovieRepositoriesImpl
import com.example.data.data.domainRepositoryImpl.network.person.PersonRepositoriesImpl
import com.example.data.data.domainRepositoryImpl.storage.MovieStorageRepositoryImpl
import com.example.data.storage.source.movie.MovieStorageDataSource
import com.example.data.storage.source.movie.MovieStorageDataSourceImpl
import com.example.domain.repositories.network.movie.MovieRepositories
import com.example.domain.repositories.network.person.PersonRepositories
import com.example.domain.repositories.storage.MovieStorageRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryBindModule {

    @Binds
    abstract fun bindMovieRepository(impl: MovieRepositoriesImpl): MovieRepositories

    @Binds
    abstract fun bindPersonRepository(impl: PersonRepositoriesImpl): PersonRepositories

    @Binds
    abstract fun bindMovieStorageRepository(impl: MovieStorageRepositoryImpl): MovieStorageRepository

    @Binds
    abstract fun bindCloudDataSourceMovie(impl: CloudDataSourceMovieImpl): CloudDataSourceMovie

    @Binds
    abstract fun bindCloudDataSourcePerson(impl: CloudDataSourcePersonImpl): CloudDataSourcePerson

    @Binds
    abstract fun bindCloudDataSourceStorage(impl: MovieStorageDataSourceImpl): MovieStorageDataSource
}