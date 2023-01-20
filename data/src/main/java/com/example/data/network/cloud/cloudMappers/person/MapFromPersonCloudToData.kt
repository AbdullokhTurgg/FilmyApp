package com.example.data.network.cloud.cloudMappers.person

import com.example.data.dataModel.movie.MovieData
import com.example.data.dataModel.person.PersonData
import com.example.data.network.cloud.cloudModels.movie.MovieCloud
import com.example.data.network.cloud.cloudModels.person.PersonCloud
import com.example.domain.base.BaseMapper

class MapFromPersonCloudToData(
    private val mapFromListMovieCloudToData: BaseMapper<List<MovieCloud>, List<MovieData>>,
) : BaseMapper<PersonCloud, PersonData> {
    override fun map(from: PersonCloud) = from.run {
        PersonData(profile_path = profile_path,
            adult = adult,
            id = id,
            known_for = mapFromListMovieCloudToData.map(known_for),
            name = name,
            popularity = popularity)
    }
}