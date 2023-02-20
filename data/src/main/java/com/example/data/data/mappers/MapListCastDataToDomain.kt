package com.example.data.data.mappers

import com.example.data.data.models.movie.CastData
import com.example.domain.base.BaseMapper
import com.example.domain.models.movie.CastDomain

class MapListCastDataToDomain(private val mapper: BaseMapper<CastData, CastDomain>) :
    BaseMapper<List<CastData>, List<CastDomain>> {
    override fun map(from: List<CastData>) = from.map {
        mapper.map(it)
    }
}