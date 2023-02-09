package com.example.movieappazi.di

import com.example.data.dataModel.movie.CreditsResponseData
import com.example.data.dataModel.movie.MovieData
import com.example.data.dataModel.movie.MovieDetailsData
import com.example.data.dataModel.movie.MoviesData
import com.example.data.dataModel.person.PersonDetailsData
import com.example.data.dataModel.person.PersonsData
import com.example.data.dataModel.video.VideosData
import com.example.data.network.cloud.cloudModels.movie.MovieDetailsCloud
import com.example.data.network.cloud.cloudModels.movie.MoviesCloud
import com.example.data.network.cloud.cloudModels.movie.movie_category.CreditsResponseCloud
import com.example.data.network.cloud.cloudModels.person.PersonDetailsCloud
import com.example.data.network.cloud.cloudModels.person.PersonsCloud
import com.example.data.network.cloud.cloudModels.video.VideosCloud
import com.example.data.network.cloud.handler.ResponseHandler
import com.example.data.network.cloud.source.movie.CloudDataSourceMovie
import com.example.data.network.cloud.source.movie.CloudDataSourceMovieImpl
import com.example.data.network.cloud.source.person.CloudDataSourcePerson
import com.example.data.network.cloud.source.person.CloudDataSourcePersonImpl
import com.example.data.network.cloud.source.video.CloudDataSourceVideo
import com.example.data.network.cloud.source.video.CloudDataSourceVideoImpl
import com.example.data.network.api.api.movie.MovieApi
import com.example.data.network.api.api.person.PersonApi
import com.example.data.network.api.api.video.VideoApi
import com.example.data.storage.db.movie.MovieDao
import com.example.data.storage.model.movie.MovieStorage
import com.example.data.storage.source.movie.MovieStorageDataSource
import com.example.data.storage.source.movie.MovieStorageDataSourceImpl
import com.example.domain.assistant.DispatchersProvider
import com.example.domain.base.BaseMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CloudRepositoryModule {

    @Provides
    @Singleton
    fun provideCloudDataSourceMovie(
        dispatchersProvider: DispatchersProvider,
        responseHandler: ResponseHandler,
        api: MovieApi,
        mapCreditsResponseCloudToData: BaseMapper<CreditsResponseCloud, CreditsResponseData>,
        mapFromMoviesCloudToData: BaseMapper<MoviesCloud, MoviesData>,
        mapFromDetailsCloudToData: BaseMapper<MovieDetailsCloud, MovieDetailsData>,
    ): CloudDataSourceMovie = CloudDataSourceMovieImpl(
        api = api,
        mapCreditsResponseCloudToData = mapCreditsResponseCloudToData,
        mapFromMoviesCloudToData = mapFromMoviesCloudToData,
        mapFromDetailsCloudToData = mapFromDetailsCloudToData,
        dispatchersProvider = dispatchersProvider,
        responseHandler = responseHandler,
    )

    @Provides
    @Singleton
    fun provideCloudDataSourcePerson(
        personApi: PersonApi,
        mapFromPersonsCloudToData: BaseMapper<PersonsCloud, PersonsData>,
        mapFromPersonDetailsCloudToData: BaseMapper<PersonDetailsCloud, PersonDetailsData>,
        dispatchersProvider: DispatchersProvider,
        responseHandler: ResponseHandler,
    ): CloudDataSourcePerson = CloudDataSourcePersonImpl(
        personApi = personApi,
        mapFromPersonsCloudToData = mapFromPersonsCloudToData,
        mapFromPersonDetailsCloudToData = mapFromPersonDetailsCloudToData,
        dispatchersProvider = dispatchersProvider,
        responseHandler = responseHandler,
    )

    @Provides
    @Singleton
    fun provideCloudDataSourceVideo(
        videoApi: VideoApi,
        mapFromVideosCloudToData: BaseMapper<VideosCloud, VideosData>,
        dispatchersProvider: DispatchersProvider,
    ): CloudDataSourceVideo = CloudDataSourceVideoImpl(
        videoApi = videoApi,
        mapFromVideosCloudToData = mapFromVideosCloudToData,
        dispatchersProvider = dispatchersProvider,
    )

    @Provides
    @Singleton
    fun provideCloudDataSourceStorage(
        movieDao: MovieDao,
        mapFromStorage: BaseMapper<MovieStorage, MovieData>,
        mapToStorage: BaseMapper<MovieData, MovieStorage>,
        dispatchersProvider: DispatchersProvider,
    ): MovieStorageDataSource = MovieStorageDataSourceImpl(
        movieDao = movieDao,
        mapFromStorage = mapFromStorage,
        mapToStorage = mapToStorage,
        dispatchersProvider = dispatchersProvider,
    )

//    @Provides
//    @Singleton
//    fun provideCloudPersonStorage(
//        personDao: PersonDao,
//        mapFromStorageToDataPerson: BaseMapper<PersonStorage, PersonDetailsData>,
//        mapFromPersonDetailsDataToStorage: BaseMapper<PersonDetailsData, PersonStorage>,
//        mapListFromMovieDetailsDataToStorage: BaseMapper<List<PersonStorage>, List<PersonDetailsData>>,
//    ): PersonStorageDataSource = PersonStorageDataSourceImpl(
//        personDao = personDao,
//        mapFromStorageToDataPerson = mapFromStorageToDataPerson,
//        mapFromPersonDetailsDataToStorage = mapFromPersonDetailsDataToStorage,
//        mapListFromMovieDetailsDataToStorage = mapListFromMovieDetailsDataToStorage,
//    )

}