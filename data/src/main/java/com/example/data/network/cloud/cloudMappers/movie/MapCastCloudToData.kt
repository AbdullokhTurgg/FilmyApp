package com.example.data.network.cloud.cloudMappers.movie

import com.example.data.dataModel.movie.CastData
import com.example.data.network.cloud.cloudModels.movie.movie_category.CastCloud
import com.example.domain.base.BaseMapper

class MapCastCloudToData : BaseMapper<CastCloud, CastData> {
    override fun map(from: CastCloud) = from.run {
        CastData(
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