package com.example.data.storage.tv.mappers

import com.example.data.data.models.movie.tv_shows.SeriesData
import com.example.data.storage.tv.models.TvStorage
import com.example.domain.base.BaseMapper

class MapListTvStorageToData(private val mapper: BaseMapper<TvStorage, SeriesData>) :
    BaseMapper<List<TvStorage>, List<SeriesData>> {
    override fun map(from: List<TvStorage>) = from.map {
        mapper.map(it)
    }
}