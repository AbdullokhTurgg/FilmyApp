package com.example.data.cloud.mappers.movie

import com.example.data.cloud.models.movie.tv_shows.SeriesCloud
import com.example.data.cloud.models.movie.tv_shows.TvSeriesResponseCloud
import com.example.data.data.models.movie.tv_shows.SeriesData
import com.example.data.data.models.movie.tv_shows.TvSeriesResponseData
import com.example.domain.base.BaseMapper
import javax.inject.Inject

class MapFromTvResponseCloudToData @Inject constructor(
    private val mapper: BaseMapper<List<SeriesCloud>, List<SeriesData>>,
) : BaseMapper<TvSeriesResponseCloud, TvSeriesResponseData> {
    override fun map(from: TvSeriesResponseCloud) = from.run {
        TvSeriesResponseData(page = page,
            results = mapper.map(results),
            total_pages = total_pages,
            total_results = total_results)
    }
}