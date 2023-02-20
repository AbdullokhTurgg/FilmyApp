package com.example.movieappazi.app.mappers

import com.example.domain.base.BaseMapper
import com.example.domain.models.movie.CastDomain
import com.example.movieappazi.app.models.movie.CastUi
import javax.inject.Inject

class MapCastDomainToUi @Inject constructor(): BaseMapper<CastDomain, CastUi> {
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