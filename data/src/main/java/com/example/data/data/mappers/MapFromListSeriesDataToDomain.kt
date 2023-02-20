package com.example.data.data.mappers

import com.example.data.data.models.movie.tv_shows.SeriesData
import com.example.domain.base.BaseMapper
import com.example.domain.models.movie.tv_shows.SeriesDomain

class MapFromListSeriesDataToDomain(private val mapper: BaseMapper<SeriesData, SeriesDomain>) :
    BaseMapper<List<SeriesData>, List<SeriesDomain>> {
    override fun map(from: List<SeriesData>) = from.map {
        mapper.map(it)
    }
}