package com.example.movieappazi.app.mappers

import com.example.domain.base.BaseMapper
import com.example.domain.domainModels.movie.CastDomain
import com.example.domain.domainModels.movie.CreditsResponseDomain
import com.example.movieappazi.app.models.movie.CastUi
import com.example.movieappazi.app.models.movie.CreditsResponseUi
import javax.inject.Inject

class MapCreditsResponseDomainToUi @Inject constructor(private val mapper: BaseMapper<List<CastDomain>, List<CastUi>>) :
    BaseMapper<CreditsResponseDomain, CreditsResponseUi> {
    override fun map(from: CreditsResponseDomain) = from.run {
        CreditsResponseUi(
            id = id,
            cast = mapper.map(cast)
        )
    }
}