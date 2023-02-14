package com.example.data.data.mappers

import com.example.data.data.models.movie.MovieData
import com.example.data.data.models.person.PersonData
import com.example.domain.base.BaseMapper
import com.example.domain.domainModels.movie.MovieDomain
import com.example.domain.domainModels.person.PersonDomain
import javax.inject.Inject

class MapFromPersonDataToDomain @Inject constructor(
    private val mapFromListMovieDataToDomain: BaseMapper<List<MovieData>, List<MovieDomain>>,
) : BaseMapper<PersonData, PersonDomain> {
    override fun map(from: PersonData) = from.run {
        PersonDomain(
            profile_path = profile_path,
            adult = adult,
            id = id,
            known_for = mapFromListMovieDataToDomain.map(known_for),
            name = name,
            popularity = popularity
        )
    }
}