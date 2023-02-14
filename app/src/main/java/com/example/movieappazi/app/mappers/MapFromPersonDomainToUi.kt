package com.example.movieappazi.app.mappers

import com.example.domain.base.BaseMapper
import com.example.domain.domainModels.movie.MovieDomain
import com.example.domain.domainModels.person.PersonDomain
import com.example.movieappazi.app.models.movie.MovieUi
import com.example.movieappazi.app.models.person.PersonUi
import javax.inject.Inject

class MapFromPersonDomainToUi @Inject constructor(
    private val mapFromListMovieUiToDomain: BaseMapper<List<MovieDomain>, List<MovieUi>>,
) : BaseMapper<PersonDomain, PersonUi> {
    override fun map(from: PersonDomain) = from.run {
        PersonUi(profile_path = profile_path,
            adult = adult,
            id = id,
            known_for = mapFromListMovieUiToDomain.map(known_for),
            name = name,
            popularity = popularity
        )
    }
}