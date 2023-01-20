package com.example.movieappazi.di

import com.example.data.dataMappers.*
import com.example.data.dataModel.movie.MovieData
import com.example.data.dataModel.movie.MovieDetailsData
import com.example.data.dataModel.movie.MovieGenresData
import com.example.data.dataModel.movie.MoviesData
import com.example.data.dataModel.person.PersonData
import com.example.data.dataModel.person.PersonDetailsData
import com.example.data.dataModel.person.PersonsData
import com.example.data.network.cloud.cloudMappers.*
import com.example.data.network.cloud.cloudMappers.movie.*
import com.example.data.network.cloud.cloudMappers.person.MapFromListPersonCloudToData
import com.example.data.network.cloud.cloudMappers.person.MapFromPersonCloudToData
import com.example.data.network.cloud.cloudMappers.person.MapFromPersonDetailsCloudToData
import com.example.data.network.cloud.cloudMappers.person.MapFromPersonsCloudToData
import com.example.data.network.cloud.cloudModels.movie.MovieCloud
import com.example.data.network.cloud.cloudModels.movie.MovieDetailsCloud
import com.example.data.network.cloud.cloudModels.movie.MovieGenresCloud
import com.example.data.network.cloud.cloudModels.movie.MoviesCloud
import com.example.data.network.cloud.cloudModels.person.PersonCloud
import com.example.data.network.cloud.cloudModels.person.PersonDetailsCloud
import com.example.data.network.cloud.cloudModels.person.PersonsCloud
import com.example.data.storage.mappers.MapFromMovieDataToStorage
import com.example.data.storage.mappers.MapFromMovieStorageToData
import com.example.data.storage.model.MovieStorage
import com.example.domain.base.BaseMapper
import com.example.domain.domainModels.movie.MovieDetailsDomain
import com.example.domain.domainModels.movie.MovieDomain
import com.example.domain.domainModels.movie.MovieGenresDomain
import com.example.domain.domainModels.movie.MoviesDomain
import com.example.domain.domainModels.person.PersonDetailsDomain
import com.example.domain.domainModels.person.PersonDomain
import com.example.domain.domainModels.person.PersonsDomain
import com.example.movieappazi.uiMappers.*
import com.example.movieappazi.uiModels.movie.MovieDetailsUi
import com.example.movieappazi.uiModels.movie.MovieGenresUi
import com.example.movieappazi.uiModels.movie.MovieUi
import com.example.movieappazi.uiModels.movie.MoviesUi
import com.example.movieappazi.uiModels.person.PersonDetailsUi
import com.example.movieappazi.uiModels.person.PersonUi
import com.example.movieappazi.uiModels.person.PersonsUi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class MapperModule {

    // Provide Data Module Mappers  <<!>>
    @Provides
    fun provideMapperFromMovieDomainToData(): BaseMapper<MovieDomain, MovieData> =
        MapFromMovieDomainToData()

    @Provides
    fun provideMapperFromListOfMovieDataToDomain(
        mapFromMovieDataToDomain: BaseMapper<MovieData, MovieDomain>,
    ): BaseMapper<List<MovieData>, List<MovieDomain>> =
        MapFromListOfMovieDataToDomain(mapFromMovieDataToDomain = mapFromMovieDataToDomain)

    @Provides
    fun provideMapperFromListOfMovieGenresDataToDomain(
        mapFromMovieGenresDataToDomain: BaseMapper<MovieGenresData, MovieGenresDomain>,
    ): BaseMapper<List<MovieGenresData>, List<MovieGenresDomain>> =
        MapFromListOfMovieGenresDataToDomain(mapFromMovieGenresDataToDomain = mapFromMovieGenresDataToDomain)

    @Provides
    fun provideMapperFromMovieDataToDomain(): BaseMapper<MovieData, MovieDomain> =
        MapFromMovieDataToMovieDomain()

    @Provides
    fun provideMapperFromMovieDetailsDataToDomain(
        mapFromListOfMovieGenresDataToDomain: BaseMapper<List<MovieGenresData>, List<MovieGenresDomain>>,
    ): BaseMapper<MovieDetailsData, MovieDetailsDomain> =
        MapFromMovieDetailsDataToDomain(mapFromListOfMovieGenresDataToDomain = mapFromListOfMovieGenresDataToDomain)

    @Provides
    fun provideMapperFromMoviesDataToDomain(
        mapListOfMovieDataToDomain: BaseMapper<List<MovieData>, List<MovieDomain>>,
    ): BaseMapper<MoviesData, MoviesDomain> =
        MapFromMoviesDataToDomain(mapListOfMovieDataToDomain = mapListOfMovieDataToDomain)

    @Provides
    fun provideMapperFromMovieGenresDataToDomain(): BaseMapper<MovieGenresData, MovieGenresDomain> =
        MapFromMovieGenresDataToDomain()

    // Provide Cloud Mappers  <<!>>
    @Provides
    fun provideMapperFromListMovieGenresCloudToData(
        mapFromMovieGenresCloudToData: BaseMapper<MovieGenresCloud, MovieGenresData>,
    ): BaseMapper<List<MovieGenresCloud>, List<MovieGenresData>> =
        MapFromListMovieGenresCloudToData(mapFromMovieGenresCloudToData = mapFromMovieGenresCloudToData)

    @Provides
    fun provideMapperFromListOfMovieCloudToData(
        mapFromMovieCloudToData: BaseMapper<MovieCloud, MovieData>,
    ): BaseMapper<List<MovieCloud>, List<MovieData>> =
        MapFromListOfMovieCloudToData(mapFromMovieCloudToData = mapFromMovieCloudToData)

    @Provides
    fun provideMapperFromMovieCloudToData(): BaseMapper<MovieCloud, MovieData> =
        MapFromMovieCloudToData()

    @Provides
    fun provideMapperFromMovieDetailsCloudToData(
        mapFromListMovieGenresCloudToData: BaseMapper<List<MovieGenresCloud>, List<MovieGenresData>>,
    ): BaseMapper<MovieDetailsCloud, MovieDetailsData> =
        MapFromMovieDetailsCloudToData(mapFromListMovieGenresCloudToData = mapFromListMovieGenresCloudToData)

    @Provides
    fun provideMapperFromMoviesCloudToData(
        mapFromMovieCloudToData: BaseMapper<List<MovieCloud>, List<MovieData>>,
    ): BaseMapper<MoviesCloud, MoviesData> =
        MapFromMoviesCloudToData(mapFromMovieCloudToData = mapFromMovieCloudToData)

    @Provides
    fun provideMapperFromMovieGenresCloudToData(): BaseMapper<MovieGenresCloud, MovieGenresData> =
        MapMovieGenresCloudToData()


    // Provide App Module Mappers <<!>>
    @Provides
    fun provideMapperFromListMovieDomainToUi(
        mapFromMovieDomainToUi: BaseMapper<MovieDomain, MovieUi>,
    ): BaseMapper<List<MovieDomain>, List<MovieUi>> =
        MapFromListOfMovieDomainToUi(mapFromMovieDomainToUi = mapFromMovieDomainToUi)

    @Provides
    fun provideMapperFromListMovieGenresDomainToUi(
        mapFromMovieGenresDomainToUi: BaseMapper<MovieGenresDomain, MovieGenresUi>,
    ): BaseMapper<List<MovieGenresDomain>, List<MovieGenresUi>> =
        MapFromListOfMovieGenresDomainToUi(mapFromMovieGenresDomainToUi = mapFromMovieGenresDomainToUi)

    @Provides
    fun provideMapperFromMovieDetailsDomainToUi(
        mapFromListOfMovieGenresDomainToUi: BaseMapper<List<MovieGenresDomain>, List<MovieGenresUi>>,
    ): BaseMapper<MovieDetailsDomain, MovieDetailsUi> =
        MapFromMovieDetailsDomainToUi(mapFromListOfMovieGenresDomainToUi = mapFromListOfMovieGenresDomainToUi)

    @Provides
    fun provideMapperFromMovieDomainToUi(): BaseMapper<MovieDomain, MovieUi> =
        MapFromMovieDomainToMovieUi()

    @Provides
    fun provideMapperFromMovieGenresDomainToUi(): BaseMapper<MovieGenresDomain, MovieGenresUi> =
        MapFromMovieGenresDomainToUi()

    @Provides
    fun provideMapperFromMoviesDomainToUi(
        mapFromListMovieDomainToUi: BaseMapper<List<MovieDomain>, List<MovieUi>>,
    ): BaseMapper<MoviesDomain, MoviesUi> =
        MapFromMoviesDomainToUi(mapFromListMovieDomainToUi = mapFromListMovieDomainToUi)

    @Provides
    fun provideMapperFromMovieStorageToData(): BaseMapper<MovieStorage, MovieData> =
        MapFromMovieStorageToData()

    @Provides
    fun provideMapperFromMovieDataToStorage(): BaseMapper<MovieData, MovieStorage> =
        MapFromMovieDataToStorage()

    @Provides
    fun provideMapperFromPersonDetailsDomainToUi(): BaseMapper<PersonDetailsDomain, PersonDetailsUi> =
        MapFromPersonDetailsDomainToUi()

    @Provides
    fun provideMapperFromPersonDetailsUiToDomain(): BaseMapper<PersonDetailsUi, PersonDetailsDomain> =
        MapFromPersonDetailsUiToDomain()

    @Provides
    fun provideMapperFromListOfPersonDetDomainToUi(
        mapFromPersonDetDomainToUi: BaseMapper<PersonDetailsDomain, PersonDetailsUi>,
    ): BaseMapper<List<PersonDetailsDomain>, List<PersonDetailsUi>> =
        MapFromLisOfPersonDetDomainToUi(mapFromPersonDetDomainToUi = mapFromPersonDetDomainToUi)

    @Provides
    fun provideMapperFromMovieUiToDomain(): BaseMapper<MovieUi, MovieDomain> =
        MapFromMovieUiToDomain()

    @Provides
    fun provideMapperFromPersonsDataToDomain(
        mapFromListPersonDataToDomain: BaseMapper<List<PersonData>, List<PersonDomain>>,
    ): BaseMapper<PersonsData, PersonsDomain> =
        MapPersonsDataToDomain(mapFromListPersonDataToDomain = mapFromListPersonDataToDomain)

    @Provides
    fun provideMapperFromLisPersonDataToDomain(
        mapFromPersonDataToDomain: BaseMapper<PersonData, PersonDomain>,
    ): BaseMapper<List<PersonData>, List<PersonDomain>> =
        MapFromListPersonDataToDomain(mapFromPersonDataToDomain = mapFromPersonDataToDomain)

    @Provides
    fun provideMapperFromPersonDataToDomain(
        mapFromListMovieDataToDomain: BaseMapper<List<MovieData>, List<MovieDomain>>,
    ): BaseMapper<PersonData, PersonDomain> =
        MapFromPersonDataToDomain(mapFromListMovieDataToDomain = mapFromListMovieDataToDomain)

    @Provides
    fun provideMapperFromPersonDetailsDataToDomain(): BaseMapper<PersonDetailsData, PersonDetailsDomain> =
        MapFromPersonDetailsDataToDomain()

    @Provides
    fun provideMapperFromPersonsCloudToData(
        mapFromListPersonDataToDomain: BaseMapper<List<PersonCloud>, List<PersonData>>,
    ): BaseMapper<PersonsCloud, PersonsData> =
        MapFromPersonsCloudToData(mapFromListPersonDataToDomain = mapFromListPersonDataToDomain)

    @Provides
    fun provideMapperFromPersonCloudToData(
        mapFromListMovieCloudToData: BaseMapper<List<MovieCloud>, List<MovieData>>,
    ): BaseMapper<PersonCloud, PersonData> =
        MapFromPersonCloudToData(mapFromListMovieCloudToData = mapFromListMovieCloudToData)

    @Provides
    fun provideMapperFromListPersonCloudToData(
        mapFromPersonCloudToData: BaseMapper<PersonCloud, PersonData>,
    ): BaseMapper<List<PersonCloud>, List<PersonData>> =
        MapFromListPersonCloudToData(mapFromPersonCloudToData = mapFromPersonCloudToData)

    @Provides
    fun provideMapperFromPersonDetailsCloudToData(): BaseMapper<PersonDetailsCloud, PersonDetailsData> =
        MapFromPersonDetailsCloudToData()

    @Provides
    fun provideMapperFromPersonDomainToUi(
        mapFromListMovieUiToDomain: BaseMapper<List<MovieDomain>, List<MovieUi>>,
    ): BaseMapper<PersonDomain, PersonUi> =
        MapFromPersonDomainToUi(mapFromListMovieUiToDomain = mapFromListMovieUiToDomain)

    @Provides
    fun provideMapperFromPersonsDomainToUi(
        mapFromListPersonDomainToUi: BaseMapper<List<PersonDomain>, List<PersonUi>>,
    ): BaseMapper<PersonsDomain, PersonsUi> =
        MapFromPersonsDomainToUi(mapFromListPersonDomainToUi = mapFromListPersonDomainToUi)

    @Provides
    fun provideMapperFromListPersonDomainToUi(
        mapFromPersonDomainToUi: BaseMapper<PersonDomain, PersonUi>,
    ): BaseMapper<List<PersonDomain>, List<PersonUi>> =
        MapFromListPersonDomainToUi(mapFromPersonDomainToUi = mapFromPersonDomainToUi)
}