package com.example.data.data.mappers

import com.example.data.data.models.movie.tv_shows.SeriesData
import com.example.data.data.models.movie.tv_shows.TvSeriesResponseData
import com.example.domain.base.BaseMapper
import com.example.domain.models.movie.tv_shows.SeriesDomain
import com.example.domain.models.movie.tv_shows.TvSeriesResponseDomain
import javax.inject.Inject

class MapFromTvResponseDataToDomain @Inject constructor(private val mapper: BaseMapper<List<SeriesData>, List<SeriesDomain>>) :
    BaseMapper<TvSeriesResponseData, TvSeriesResponseDomain> {
    override fun map(from: TvSeriesResponseData) = from.run {
        TvSeriesResponseDomain(page = page,
            results = mapper.map(results),
            total_pages = total_pages,
            total_results = total_results)
    }
}