package com.example.movieappazi.app.mappers

import com.example.domain.base.BaseMapper
import com.example.domain.models.movie.tv_shows.SeriesDomain
import com.example.domain.models.movie.tv_shows.TvSeriesResponseDomain
import com.example.movieappazi.app.models.movie.tv_shows.SeriesUi
import com.example.movieappazi.app.models.movie.tv_shows.TvSeriesResponseUi
import javax.inject.Inject

class MapFromTvResponseDomainToUi @Inject constructor(private val mapper: BaseMapper<List<SeriesDomain>, List<SeriesUi>>) :
    BaseMapper<TvSeriesResponseDomain, TvSeriesResponseUi> {
    override fun map(from: TvSeriesResponseDomain) = from.run {
        TvSeriesResponseUi(
            page = page,
            series = mapper.map(results),
            total_pages = total_pages,
            total_results = total_results
        )
    }
}