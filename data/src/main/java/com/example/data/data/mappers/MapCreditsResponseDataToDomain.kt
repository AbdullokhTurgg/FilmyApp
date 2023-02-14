package com.example.data.data.mappers

import com.example.data.data.models.movie.CastData
import com.example.data.data.models.movie.CreditsResponseData
import com.example.domain.base.BaseMapper
import com.example.domain.domainModels.movie.CastDomain
import com.example.domain.domainModels.movie.CreditsResponseDomain
import javax.inject.Inject

class MapCreditsResponseDataToDomain @Inject constructor(private val mapper: BaseMapper<List<CastData>, List<CastDomain>>) :
    BaseMapper<CreditsResponseData, CreditsResponseDomain> {
    override fun map(from: CreditsResponseData) = from.run {
        CreditsResponseDomain(id = id, cast = mapper.map(cast))
    }
}