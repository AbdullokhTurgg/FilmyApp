package com.example.movieappazi.uiMappers

import com.example.data.dataModel.movie.CreditsResponseData
import com.example.domain.base.BaseMapper
import com.example.domain.domainModels.movie.CastDomain
import com.example.domain.domainModels.movie.CreditsResponseDomain
import com.example.movieappazi.uiModels.movie.CastUi
import com.example.movieappazi.uiModels.movie.CreditsResponseUi

class MapCreditsResponseDomainToUi(private val mapper: BaseMapper<List<CastDomain>, List<CastUi>>) :
    BaseMapper<CreditsResponseDomain, CreditsResponseUi> {
    override fun map(from: CreditsResponseDomain) = from.run {
        CreditsResponseUi(
            id = id,
            cast = mapper.map(cast)
        )
    }
}