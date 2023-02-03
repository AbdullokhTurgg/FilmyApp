package com.example.data.network.cloud.cloudMappers.movie

import com.example.data.dataModel.movie.CastData
import com.example.data.dataModel.movie.CreditsResponseData
import com.example.data.network.cloud.cloudModels.movie.movie_category.CastCloud
import com.example.data.network.cloud.cloudModels.movie.movie_category.CreditsResponseCloud
import com.example.domain.base.BaseMapper

class MapCreditsResponseCloudToData(private val mapper: BaseMapper<List<CastCloud>, List<CastData>>) :
    BaseMapper<CreditsResponseCloud, CreditsResponseData> {
    override fun map(from: CreditsResponseCloud) = from.run {
        CreditsResponseData(
            id = id,
            cast = mapper.map(cast)
        )
    }
}