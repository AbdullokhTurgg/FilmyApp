package com.example.data.dataMappers

import com.example.data.dataModel.movie.CastData
import com.example.domain.base.BaseMapper
import com.example.domain.domainModels.movie.CastDomain

class MapCastDataToDomain : BaseMapper<CastData, CastDomain> {
    override fun map(from: CastData) = from.run {
        CastDomain(
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