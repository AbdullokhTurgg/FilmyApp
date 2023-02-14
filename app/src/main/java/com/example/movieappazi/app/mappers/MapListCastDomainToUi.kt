package com.example.movieappazi.app.mappers

import com.example.domain.base.BaseMapper
import com.example.domain.domainModels.movie.CastDomain
import com.example.movieappazi.app.models.movie.CastUi

class MapListCastDomainToUi(private val mapper: BaseMapper<CastDomain, CastUi>) :
    BaseMapper<List<CastDomain>, List<CastUi>> {
    override fun map(from: List<CastDomain>) = from.map {
        mapper.map(it)
    }
}