package com.example.movieappazi.app.mappers

import com.example.domain.base.BaseMapper
import com.example.domain.models.movie.tv_shows.SeriesDomain
import com.example.movieappazi.app.models.movie.tv_shows.SeriesUi
import javax.inject.Inject

class MapFromListSeriesDomainToUi @Inject constructor(private val mapper: BaseMapper<SeriesDomain, SeriesUi>) :
    BaseMapper<List<SeriesDomain>, List<SeriesUi>> {
    override fun map(from: List<SeriesDomain>) = from.map {
        mapper.map(it)
    }
}