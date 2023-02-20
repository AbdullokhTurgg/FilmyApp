package com.example.movieappazi.app.di

import com.example.data.cloud.mappers.movie.*
import com.example.data.cloud.mappers.person.MapFromListPersonCloudToData
import com.example.data.cloud.models.movie.MovieCloud
import com.example.data.cloud.models.movie.MovieGenresCloud
import com.example.data.cloud.models.movie.movie_category.CastCloud
import com.example.data.cloud.models.movie.tv_shows.SeriesCloud
import com.example.data.cloud.models.person.PersonCloud
import com.example.data.data.mappers.*
import com.example.data.data.models.movie.*
import com.example.data.data.models.movie.tv_shows.SeriesData
import com.example.data.data.models.person.PersonData
import com.example.data.storage.tv.mappers.MapListTvStorageToData
import com.example.data.storage.tv.models.TvStorage
import com.example.domain.base.BaseMapper
import com.example.domain.models.movie.*
import com.example.domain.models.movie.tv_shows.SeriesDomain
import com.example.domain.models.person.PersonDetailsDomain
import com.example.domain.models.person.PersonDomain
import com.example.movieappazi.app.mappers.*
import com.example.movieappazi.app.models.movie.*
import com.example.movieappazi.app.models.movie.tv_shows.SeriesUi
import com.example.movieappazi.app.models.person.PersonDetailsUi
import com.example.movieappazi.app.models.person.PersonUi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object MapperModule {

    @Provides
    fun provideMapListTvStorageToData(
        mapper: BaseMapper<TvStorage, SeriesData>,
    ): BaseMapper<List<TvStorage>, List<SeriesData>> = MapListTvStorageToData(mapper = mapper)

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

    @Provides
    fun provideMapFromListSeriesCloudToData(mapper: BaseMapper<SeriesCloud, SeriesData>): BaseMapper<List<SeriesCloud>, List<SeriesData>> =
        MapFromListSeriesCloudToData(mapper = mapper)

    @Provides
    fun provideMapFromListSeriesDataToDomain(mapper: BaseMapper<SeriesData, SeriesDomain>): BaseMapper<List<SeriesData>, List<SeriesDomain>> =
        MapFromListSeriesDataToDomain(mapper = mapper)

    @Provides
    fun provideMapperFromListSeriesDomainToUi(mapper: BaseMapper<SeriesDomain, SeriesUi>): BaseMapper<List<SeriesDomain>, List<SeriesUi>> =
        MapFromListSeriesDomainToUi(mapper = mapper)
}