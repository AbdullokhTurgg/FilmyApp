package com.example.movieappazi.app.mappers

import com.example.domain.base.BaseMapper
import com.example.domain.models.movie.tv_shows.TvSeriesDetailsDomain
import com.example.movieappazi.app.models.movie.tv_shows.TvSeriesDetailsUi
import javax.inject.Inject

class MapFromTvSeriesDetailsDomainToUi @Inject constructor(
) : BaseMapper<TvSeriesDetailsDomain, TvSeriesDetailsUi> {
    override fun map(from: TvSeriesDetailsDomain) = from.run {
        TvSeriesDetailsUi(adult = adult,
            backdropPath = backdropPath,
            episodeRunTime = episodeRunTime.map { episodeRunTime -> episodeRunTime },
            firstAirDate = firstAirDate,
            homepage = homepage,
            id = id,
            inProduction = inProduction,
            languages = languages.map { languages -> languages },
            lastAirDate = lastAirDate,
            name = name,
            numberOfEpisodes = numberOfEpisodes,
            numberOfSeasons = numberOfSeasons,
            originCountry = originCountry.map { originCountry -> originCountry },
            originalLanguage = originalLanguage,
            originalName = originalName,
            overview = overview,
            popularity = popularity,
            posterPath = posterPath,
            status = status,
            tagline = tagline,
            type = type,
            voteAverage = voteAverage,
            voteCount = voteCount)
    }
}