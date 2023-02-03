package com.example.movieappazi.uiMappers

import com.example.domain.base.BaseMapper
import com.example.domain.domainModels.movie.CastDomain
import com.example.movieappazi.uiModels.movie.CastUi

class MapCastDomainToUi: BaseMapper<CastDomain, CastUi> {
    override fun map(from: CastDomain) = from.run {
        CastUi(
            adult = adult,
            castId = castId,
            character = character,
            creditId = creditId,
            gender = gender,
            id = id,
            knownForDepartment = knownForDepartment,
            name = name,
            order = order,
            originalName = originalName,
            popularity = popularity,
            profilePath = profilePath
        )
    }
}