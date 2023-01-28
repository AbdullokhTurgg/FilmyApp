package com.example.movieappazi.di

import android.content.Context
import com.example.data.dataModel.movie.CreditsResponseData
import com.example.data.dataModel.movie.MovieData
import com.example.data.dataModel.movie.MovieDetailsData
import com.example.data.dataModel.movie.MoviesData
import com.example.data.dataModel.person.PersonDetailsData
import com.example.data.dataModel.person.PersonsData
import com.example.data.dataModel.video.VideosData
import com.example.data.domainRepositoryImpl.network.movie.MovieRepositoriesImpl
import com.example.data.domainRepositoryImpl.network.person.PersonRepositoriesImpl
import com.example.data.domainRepositoryImpl.network.video.VideoRepositoryImpl
import com.example.data.domainRepositoryImpl.storage.MovieStorageRepositoryImpl
import com.example.data.network.cloud.base.ResourceProvider
import com.example.data.network.cloud.source.movie.CloudDataSourceMovie
import com.example.data.network.cloud.source.person.CloudDataSourcePerson
import com.example.data.network.cloud.source.video.CloudDataSourceVideo
import com.example.data.storage.source.MovieStorageDataSource
import com.example.domain.assistant.DispatchersProvider
import com.example.domain.assistant.Resource
import com.example.domain.base.BaseMapper
import com.example.domain.domainModels.movie.CreditsResponseDomain
import com.example.domain.domainModels.movie.MovieDetailsDomain
import com.example.domain.domainModels.movie.MovieDomain
import com.example.domain.domainModels.movie.MoviesDomain
import com.example.domain.domainModels.person.PersonDetailsDomain
import com.example.domain.domainModels.person.PersonsDomain
import com.example.domain.domainModels.video.VideosDomain
import com.example.domain.domainRepositories.network.movie.MovieRepositories
import com.example.domain.domainRepositories.network.person.PersonRepositories
import com.example.domain.domainRepositories.network.video.VideoRepository
import com.example.domain.domainRepositories.storage.MovieStorageRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideMovieRepository(
        dispatchersProvider: DispatchersProvider,
        cloudDataSourceMovie: CloudDataSourceMovie,
        mapCreditsResponseData: BaseMapper<CreditsResponseData, CreditsResponseDomain>,
        mapFromDetailsCloudToData: BaseMapper<MovieDetailsData, MovieDetailsDomain>,
        mapFromMoviesDataToDomain: BaseMapper<MoviesData, MoviesDomain>,
    ): MovieRepositories = MovieRepositoriesImpl(
        cloudDataSourceMovie = cloudDataSourceMovie,
        mapFromMoviesDataToDomain = mapFromMoviesDataToDomain,
        mapFromDetailsCloudToData = mapFromDetailsCloudToData,
        dispatchersProvider = dispatchersProvider,
        mapCreditsResponseData = mapCreditsResponseData,

        )

    @Provides
    @Singleton
    fun providePersonRepository(
        dataSourcePerson: CloudDataSourcePerson,
        mapFromPersonsDataToDomain: BaseMapper<PersonsData, PersonsDomain>,
        mapFromPersonsDetailsDataToDomain: BaseMapper<PersonDetailsData, PersonDetailsDomain>,
        dispatchersProvider: DispatchersProvider,
    ): PersonRepositories = PersonRepositoriesImpl(dataSourcePerson = dataSourcePerson,
        mapFromPersonsDataToDomain = mapFromPersonsDataToDomain,
        mapFromPersonsDetailsDataToDomain = mapFromPersonsDetailsDataToDomain,
        dispatchersProvider = dispatchersProvider)

    @Provides
    @Singleton
    fun provideVideoRepository(
        dataSourceVideo: CloudDataSourceVideo,
        mapFromVideosDataToDomain: BaseMapper<VideosData, VideosDomain>,
        dispatchersProvider: DispatchersProvider,
    ): VideoRepository = VideoRepositoryImpl(
        dataSourceVideo = dataSourceVideo,
        mapFromVideosDataToDomain = mapFromVideosDataToDomain,
        dispatchersProvider = dispatchersProvider,
    )

    @Provides
    @Singleton
    fun provideStorageRepository(
        dataSourceStorage: MovieStorageDataSource,
        mapFromMovieDomainToData: BaseMapper<MovieDomain, MovieData>,
        mapFromMovieDataToDomain: BaseMapper<MovieData, MovieDomain>,
    ): MovieStorageRepository = MovieStorageRepositoryImpl(
        dataSourceStorage = dataSourceStorage,
        mapFromMovieDomainToData = mapFromMovieDomainToData,
        mapFromMovieDataToDomain = mapFromMovieDataToDomain,
    )

    @Provides
    fun provideResourceProvider(
        @ApplicationContext context: Context,
    ): ResourceProvider = ResourceProvider.Base(context = context)

}