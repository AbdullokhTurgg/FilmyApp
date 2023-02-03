package com.example.data.dataMappers

import com.example.data.dataModel.movie.CastData
import com.example.data.dataModel.movie.CreditsResponseData
import com.example.data.network.cloud.cloudModels.movie.movie_category.CastCloud
import com.example.data.network.cloud.cloudModels.movie.movie_category.CreditsResponseCloud
import com.example.domain.base.BaseMapper
import com.example.domain.domainModels.movie.CastDomain
import com.example.domain.domainModels.movie.CreditsResponseDomain

class MapCreditsResponseDataToDomain(private val mapper: BaseMapper<List<CastData>, List<CastDomain>>) :
    BaseMapper<CreditsResponseData, CreditsResponseDomain> {
    override fun map(from: CreditsResponseData) = from.run {
        CreditsResponseDomain(
            id = id,
            cast = mapper.map(cast)
        )
    }
}