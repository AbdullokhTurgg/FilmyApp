package com.example.data.network.cloud.cloudMappers.movie

import com.example.data.dataModel.movie.CastData
import com.example.data.network.cloud.cloudModels.movie.movie_category.CastCloud
import com.example.domain.base.BaseMapper

class MapListCastCloudToData(private val mapper: BaseMapper<CastCloud, CastData>) :
    BaseMapper<List<CastCloud>, List<CastData>> {
    override fun map(from: List<CastCloud>) = from.map {
        mapper.map(it)
    }
}