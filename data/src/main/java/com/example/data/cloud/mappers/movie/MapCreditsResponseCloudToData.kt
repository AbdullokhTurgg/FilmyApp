package com.example.data.cloud.mappers.movie

import com.example.data.cloud.models.movie.movie_category.CastCloud
import com.example.data.cloud.models.movie.movie_category.CreditsResponseCloud
import com.example.data.data.models.movie.CastData
import com.example.data.data.models.movie.CreditsResponseData
import com.example.domain.base.BaseMapper
import javax.inject.Inject

class MapCreditsResponseCloudToData @Inject constructor(private val mapper: BaseMapper<List<CastCloud>, List<CastData>>) :
    BaseMapper<CreditsResponseCloud, CreditsResponseData> {
    override fun map(from: CreditsResponseCloud) = from.run {
        CreditsResponseData(
            id = id,
            cast = mapper.map(cast)
        )
    }
}