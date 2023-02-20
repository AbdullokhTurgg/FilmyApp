package com.example.data.cloud.mappers.movie

import com.example.data.cloud.models.movie.tv_shows.TvSeriesDetailsCloud
import com.example.data.data.models.movie.tv_shows.TvSeriesDetailsData
import com.example.domain.base.BaseMapper
import javax.inject.Inject

class MapFromTvDetailsCloudToData @Inject constructor() :
    BaseMapper<TvSeriesDetailsCloud, TvSeriesDetailsData> {
    override fun map(from: TvSeriesDetailsCloud) = from.run {
        TvSeriesDetailsData(adult = adult,
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