package com.example.data.dataMappers

import com.example.data.dataModel.movie.CastData
import com.example.domain.base.BaseMapper
import com.example.domain.domainModels.movie.CastDomain

class MapListCastDataToDomain(private val mapper: BaseMapper<CastData, CastDomain>) :
    BaseMapper<List<CastData>, List<CastDomain>> {
    override fun map(from: List<CastData>) = from.map {
        mapper.map(it)
    }
}