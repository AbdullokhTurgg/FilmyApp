package com.example.data.storage.tv.mappers

import com.example.data.data.models.movie.tv_shows.SeriesData
import com.example.data.storage.tv.models.TvStorage
import com.example.domain.base.BaseMapper
import javax.inject.Inject

class MapTvStorageToDataMaps @Inject constructor() : BaseMapper<TvStorage, SeriesData> {
    override fun map(from: TvStorage) = from.run {
        SeriesData(
            backdropPath = backdropPath,
            firstAirDate = firstAirDate,
            genreIds = genreIds.map { ids -> ids },
            id = id,
            name = name,
//            originCountry = originCountry.map { country -> country },
            originalLanguage = originalLanguage,
            originalName = originalName,
            overview = overview,
            popularity = popularity,
            posterPath = posterPath,
            voteAverage = voteAverage,
            voteCount = voteCount,
        )
    }
}