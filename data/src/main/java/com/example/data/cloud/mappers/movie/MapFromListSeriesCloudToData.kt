package com.example.data.cloud.mappers.movie

import com.example.data.cloud.models.movie.tv_shows.SeriesCloud
import com.example.data.data.models.movie.tv_shows.SeriesData
import com.example.domain.base.BaseMapper

class MapFromListSeriesCloudToData(private val mapper: BaseMapper<SeriesCloud, SeriesData>) :
    BaseMapper<List<SeriesCloud>, List<SeriesData>> {
    override fun map(from: List<SeriesCloud>) = from.map {
        mapper.map(it)
    }
}