package com.example.movieappazi.app.di

import com.example.data.cloud.mappers.movie.*
import com.example.data.cloud.mappers.person.MapFromListPersonCloudToData
import com.example.data.cloud.mappers.person.MapFromPersonCloudToData
import com.example.data.cloud.mappers.person.MapFromPersonDetailsCloudToData
import com.example.data.cloud.mappers.person.MapFromPersonsCloudToData
import com.example.data.cloud.models.movie.MovieCloud
import com.example.data.cloud.models.movie.MovieDetailsCloud
import com.example.data.cloud.models.movie.MovieGenresCloud
import com.example.data.cloud.models.movie.MoviesCloud
import com.example.data.cloud.models.movie.movie_category.CastCloud
import com.example.data.cloud.models.movie.movie_category.CreditsResponseCloud
import com.example.data.cloud.models.person.PersonCloud
import com.example.data.cloud.models.person.PersonDetailsCloud
import com.example.data.cloud.models.person.PersonsCloud
import com.example.data.data.mappers.*
import com.example.data.data.models.movie.*
import com.example.data.data.models.person.PersonData
import com.example.data.data.models.person.PersonDetailsData
import com.example.data.data.models.person.PersonsData
import com.example.data.storage.mappers.*
import com.example.data.storage.model.movie.MovieStorage
import com.example.domain.base.BaseMapper
import com.example.domain.domainModels.movie.*
import com.example.domain.domainModels.person.PersonDetailsDomain
import com.example.domain.domainModels.person.PersonDomain
import com.example.domain.domainModels.person.PersonsDomain
import com.example.movieappazi.app.mappers.*
import com.example.movieappazi.app.models.movie.*
import com.example.movieappazi.app.models.person.PersonDetailsUi
import com.example.movieappazi.app.models.person.PersonUi
import com.example.movieappazi.app.models.person.PersonsUi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object MapperModule {

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
    fun provideMapperFromListOfPersonDetDomainToUi(
        mapFromPersonDetDomainToUi: BaseMapper<PersonDetailsDomain, PersonDetailsUi>,
    ): BaseMapper<List<PersonDetailsDomain>, List<PersonDetailsUi>> =
        MapFromLisOfPersonDetDomainToUi(mapFromPersonDetDomainToUi = mapFromPersonDetDomainToUi)

    @Provides
    fun provideMapperFromLisPersonDataToDomain(
        mapFromPersonDataToDomain: BaseMapper<PersonData, PersonDomain>,
    ): BaseMapper<List<PersonData>, List<PersonDomain>> =
        MapFromListPersonDataToDomain(mapFromPersonDataToDomain = mapFromPersonDataToDomain)

    @Provides
    fun provideMapperFromListPersonCloudToData(
        mapFromPersonCloudToData: BaseMapper<PersonCloud, PersonData>,
    ): BaseMapper<List<PersonCloud>, List<PersonData>> =
        MapFromListPersonCloudToData(mapFromPersonCloudToData = mapFromPersonCloudToData)

    @Provides
    fun provideMapperFromListPersonDomainToUi(
        mapFromPersonDomainToUi: BaseMapper<PersonDomain, PersonUi>,
    ): BaseMapper<List<PersonDomain>, List<PersonUi>> =
        MapFromListPersonDomainToUi(mapFromPersonDomainToUi = mapFromPersonDomainToUi)

    @Provides
    fun provideMapListCastCloudToData(
        mapper: BaseMapper<CastCloud, CastData>,
    ): BaseMapper<List<CastCloud>, List<CastData>> = MapListCastCloudToData(mapper = mapper)

    @Provides
    fun provideMapListCastDataToDomain(mapper: BaseMapper<CastData, CastDomain>): BaseMapper<List<CastData>, List<CastDomain>> =
        MapListCastDataToDomain(mapper = mapper)

    @Provides
    fun provideMapListCastDomainToUi(mapper: BaseMapper<CastDomain, CastUi>): BaseMapper<List<CastDomain>, List<CastUi>> =
        MapListCastDomainToUi(mapper = mapper)
}