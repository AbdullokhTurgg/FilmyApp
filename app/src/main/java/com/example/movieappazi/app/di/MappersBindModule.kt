package com.example.movieappazi.app.di

import com.example.data.cloud.mappers.movie.*
import com.example.data.cloud.mappers.person.MapFromPersonCloudToData
import com.example.data.cloud.mappers.person.MapFromPersonDetailsCloudToData
import com.example.data.cloud.mappers.person.MapFromPersonsCloudToData
import com.example.data.cloud.models.movie.*
import com.example.data.cloud.models.movie.tv_shows.SeriesCloud
import com.example.data.cloud.models.movie.tv_shows.TvSeriesDetailsCloud
import com.example.data.cloud.models.movie.tv_shows.TvSeriesResponseCloud
import com.example.data.cloud.models.person.PersonCloud
import com.example.data.cloud.models.person.PersonDetailsCloud
import com.example.data.cloud.models.person.PersonsCloud
import com.example.data.data.mappers.*
import com.example.data.data.models.movie.*
import com.example.data.data.models.movie.tv_shows.SeriesData
import com.example.data.data.models.movie.tv_shows.TvSeriesDetailsData
import com.example.data.data.models.movie.tv_shows.TvSeriesResponseData
import com.example.data.data.models.person.PersonData
import com.example.data.data.models.person.PersonDetailsData
import com.example.data.data.models.person.PersonsData
import com.example.data.storage.movie.mappers.MapFromMovieDataToStorage
import com.example.data.storage.movie.mappers.MapFromMovieStorageToData
import com.example.data.storage.movie.movie.MovieStorage
import com.example.data.storage.tv.mappers.MapSeriesDataToStorage
import com.example.data.storage.tv.mappers.MapTvStorageToDataMaps
import com.example.data.storage.tv.models.TvStorage
import com.example.domain.base.BaseMapper
import com.example.domain.models.movie.*
import com.example.domain.models.movie.tv_shows.SeriesDomain
import com.example.domain.models.movie.tv_shows.TvSeriesDetailsDomain
import com.example.domain.models.movie.tv_shows.TvSeriesResponseDomain
import com.example.domain.models.person.PersonDetailsDomain
import com.example.domain.models.person.PersonDomain
import com.example.domain.models.person.PersonsDomain
import com.example.movieappazi.app.mappers.*
import com.example.movieappazi.app.models.movie.*
import com.example.movieappazi.app.models.movie.tv_shows.SeriesUi
import com.example.movieappazi.app.models.movie.tv_shows.TvSeriesDetailsUi
import com.example.movieappazi.app.models.movie.tv_shows.TvSeriesResponseUi
import com.example.movieappazi.app.models.person.PersonDetailsUi
import com.example.movieappazi.app.models.person.PersonUi
import com.example.movieappazi.app.models.person.PersonsUi
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class MappersBindModule {

    @Binds
    abstract fun bindMapSeriesDataToStorage(impl: MapSeriesDataToStorage): BaseMapper<SeriesData, TvStorage>

    @Binds
    abstract fun bindMapTvStorageToDataMaps(impl: MapTvStorageToDataMaps): BaseMapper<TvStorage, SeriesData>

    @Binds
    abstract fun bindMapFromSeriesDomainToUi(impl: MapFromSeriesDomainToUi): BaseMapper<SeriesDomain, SeriesUi>

    @Binds
    abstract fun bindMapperFromSeriesCloudToData(impl: MapFromSeriesCloudToData): BaseMapper<SeriesCloud, SeriesData>

    @Binds
    abstract fun bindMapperFromTvDetailsCloudToData(impl: MapFromTvDetailsCloudToData): BaseMapper<TvSeriesDetailsCloud, TvSeriesDetailsData>

    @Binds
    abstract fun bindMapperFromTvResponseCloudToData(impl: MapFromTvResponseCloudToData): BaseMapper<TvSeriesResponseCloud, TvSeriesResponseData>

    @Binds
    abstract fun bindMapperFromSeriesDataToDomain(impl: MapFromSeriesDataToDomain): BaseMapper<SeriesData, SeriesDomain>

    @Binds
    abstract fun bindMapperFromSeriesDomainToData(impl: MapFromSeriesDomainToData): BaseMapper<SeriesDomain, SeriesData>

    @Binds
    abstract fun bindMapperFromTvDetailsDataToDomain(impl: MapFromTvDetailsDataToDomain): BaseMapper<TvSeriesDetailsData, TvSeriesDetailsDomain>

    @Binds
    abstract fun bindMapperFromTvResponseDataToDomain(impl: MapFromTvResponseDataToDomain): BaseMapper<TvSeriesResponseData, TvSeriesResponseDomain>

    @Binds
    abstract fun bindMapperFromSeriesUiToDomain(impl: MapFromSeriesUiToDomain): BaseMapper<SeriesUi, SeriesDomain>

    @Binds
    abstract fun bindMapperFromTvResponseDomainToUi(impl: MapFromTvResponseDomainToUi): BaseMapper<TvSeriesResponseDomain, TvSeriesResponseUi>

    @Binds
    abstract fun bindMapperFromTvSeriesDetailsDomainToUi(impl: MapFromTvSeriesDetailsDomainToUi): BaseMapper<TvSeriesDetailsDomain, TvSeriesDetailsUi>

    @Binds
    abstract fun bindMovieCloudToMovieDataMapper(impl: MapFromMovieCloudToData): BaseMapper<MovieCloud, MovieData>

    @Binds
    abstract fun bindMovieDomainToDataMapper(impl: MapFromMovieDomainToData): BaseMapper<MovieDomain, MovieData>

    @Binds
    abstract fun bindMapperFromMovieDataToDomain(impl: MapFromMovieDataToMovieDomain): BaseMapper<MovieData, MovieDomain>

    @Binds
    abstract fun bindMapperFromMovieDetailsDataToDomain(impl: MapFromMovieDetailsDataToDomain): BaseMapper<MovieDetailsData, MovieDetailsDomain>

    @Binds
    abstract fun bindMapperFromMoviesDataToDomain(impl: MapFromMoviesDataToDomain): BaseMapper<MoviesData, MoviesDomain>

    @Binds
    abstract fun bindMapperFromMovieGenresDataToDomain(impl: MapFromMovieGenresDataToDomain): BaseMapper<MovieGenresData, MovieGenresDomain>

    @Binds
    abstract fun bindMapperFromMovieDetailsCloudToData(impl: MapFromMovieDetailsCloudToData): BaseMapper<MovieDetailsCloud, MovieDetailsData>

    @Binds
    abstract fun bindMapperFromMoviesCloudToData(impl: MapFromMoviesCloudToData): BaseMapper<MoviesCloud, MoviesData>

    @Binds
    abstract fun bindMapperFromMovieGenresCloudToData(impl: MapMovieGenresCloudToData): BaseMapper<MovieGenresCloud, MovieGenresData>

    @Binds
    abstract fun bindMapperFromMovieDetailsDomainToUi(impl: MapFromMovieDetailsDomainToUi): BaseMapper<MovieDetailsDomain, MovieDetailsUi>

    @Binds
    abstract fun bindMapperFromMovieDomainToUi(impl: MapFromMovieDomainToMovieUi): BaseMapper<MovieDomain, MovieUi>

    @Binds
    abstract fun bindMapperFromMovieGenresDomainToUi(impl: MapFromMovieGenresDomainToUi): BaseMapper<MovieGenresDomain, MovieGenresUi>

    @Binds
    abstract fun bindMapperFromMoviesDomainToUi(impl: MapFromMoviesDomainToUi): BaseMapper<MoviesDomain, MoviesUi>

    @Binds
    abstract fun bindMapperFromMovieStorageToData(impl: MapFromMovieStorageToData): BaseMapper<MovieStorage, MovieData>

    @Binds
    abstract fun bindMapperFromMovieDataToStorage(impl: MapFromMovieDataToStorage): BaseMapper<MovieData, MovieStorage>

    @Binds
    abstract fun bindMapperFromPersonDetailsDomainToUi(impl: MapFromPersonDetailsDomainToUi): BaseMapper<PersonDetailsDomain, PersonDetailsUi>

    @Binds
    abstract fun bindMapperFromPersonDetailsUiToDomain(impl: MapFromPersonDetailsUiToDomain): BaseMapper<PersonDetailsUi, PersonDetailsDomain>

    @Binds
    abstract fun bindMapperFromMovieUiToDomain(impl: MapFromMovieUiToDomain): BaseMapper<MovieUi, MovieDomain>

    @Binds
    abstract fun bindMapperFromPersonsDataToDomain(impl: MapPersonsDataToDomain): BaseMapper<PersonsData, PersonsDomain>

    @Binds
    abstract fun bindMapperFromPersonDataToDomain(impl: MapFromPersonDataToDomain): BaseMapper<PersonData, PersonDomain>

    @Binds
    abstract fun bindMapperFromPersonDetailsDataToDomain(impl: MapFromPersonDetailsDataToDomain): BaseMapper<PersonDetailsData, PersonDetailsDomain>

    @Binds
    abstract fun bindMapperFromPersonsCloudToData(impl: MapFromPersonsCloudToData): BaseMapper<PersonsCloud, PersonsData>

    @Binds
    abstract fun bindMapperFromPersonCloudToData(impl: MapFromPersonCloudToData): BaseMapper<PersonCloud, PersonData>

    @Binds
    abstract fun bindMapperFromPersonDetailsCloudToData(impl: MapFromPersonDetailsCloudToData): BaseMapper<PersonDetailsCloud, PersonDetailsData>

    @Binds
    abstract fun bindMapperFromPersonDomainToUi(impl: MapFromPersonDomainToUi): BaseMapper<PersonDomain, PersonUi>

    @Binds
    abstract fun bindMapperFromPersonsDomainToUi(impl: MapFromPersonsDomainToUi): BaseMapper<PersonsDomain, PersonsUi>

    @Binds
    abstract fun bindMapCreditsResponseCloudToData(impl: MapCreditsResponseCloudToData): BaseMapper<CreditsResponseCloud, CreditsResponseData>

    @Binds
    abstract fun bindMapCastCloudToData(impl: MapCastCloudToData): BaseMapper<CastCloud, CastData>

    @Binds
    abstract fun bindMapFromCreditsResponseDomainToUi(impl: MapCreditsResponseDomainToUi): BaseMapper<CreditsResponseDomain, CreditsResponseUi>

    @Binds
    abstract fun bindMapCastDataToDomain(impl: MapCastDataToDomain): BaseMapper<CastData, CastDomain>

    @Binds
    abstract fun bindMapCreditsResponseDataToDomain(impl: MapCreditsResponseDataToDomain): BaseMapper<CreditsResponseData, CreditsResponseDomain>

    @Binds
    abstract fun bindMapCastDomainToUi(impl: MapCastDomainToUi): BaseMapper<CastDomain, CastUi>

    @Binds
    abstract fun bindMapperFromPersonDetailsDomainToData(impl: MapFromPersonDetailsDomainToData): BaseMapper<PersonDetailsDomain, PersonDetailsData>
}