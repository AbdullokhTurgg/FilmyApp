package com.example.data.dataMappers

import com.example.data.dataModel.movie.MovieData
import com.example.data.dataModel.person.PersonData
import com.example.domain.base.BaseMapper
import com.example.domain.domainModels.movie.MovieDomain
import com.example.domain.domainModels.person.PersonDomain
import com.example.domain.domainModels.person.PersonsDomain

class MapFromPersonDataToDomain(
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