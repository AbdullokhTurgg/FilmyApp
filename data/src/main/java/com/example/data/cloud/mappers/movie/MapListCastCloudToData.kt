package com.example.data.cloud.mappers.movie

import com.example.data.cloud.models.movie.movie_category.CastCloud
import com.example.data.data.models.movie.CastData
import com.example.domain.base.BaseMapper

class MapListCastCloudToData(private val mapper: BaseMapper<CastCloud, CastData>) :
    BaseMapper<List<CastCloud>, List<CastData>> {
    override fun map(from: List<CastCloud>) = from.map {
        mapper.map(it)
    }
}