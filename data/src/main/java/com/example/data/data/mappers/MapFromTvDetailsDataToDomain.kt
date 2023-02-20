package com.example.data.data.mappers

import com.example.data.data.models.movie.tv_shows.TvSeriesDetailsData
import com.example.domain.base.BaseMapper
import com.example.domain.models.movie.tv_shows.TvSeriesDetailsDomain
import javax.inject.Inject

class MapFromTvDetailsDataToDomain @Inject constructor(
) : BaseMapper<TvSeriesDetailsData, TvSeriesDetailsDomain> {
    override fun map(from: TvSeriesDetailsData) = from.run {
        TvSeriesDetailsDomain(adult = adult,
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