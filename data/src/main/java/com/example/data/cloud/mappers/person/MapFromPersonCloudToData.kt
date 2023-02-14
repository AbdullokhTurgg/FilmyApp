package com.example.data.cloud.mappers.person

import com.example.data.cloud.models.movie.MovieCloud
import com.example.data.cloud.models.person.PersonCloud
import com.example.data.data.models.movie.MovieData
import com.example.data.data.models.person.PersonData
import com.example.domain.base.BaseMapper
import javax.inject.Inject

class MapFromPersonCloudToData @Inject constructor(
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