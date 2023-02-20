package com.example.movieappazi.app.mappers

import com.example.domain.base.BaseMapper
import com.example.domain.models.person.PersonDetailsDomain
import com.example.movieappazi.app.models.person.PersonDetailsUi
import javax.inject.Inject

class MapFromPersonDetailsUiToDomain @Inject constructor(): BaseMapper<PersonDetailsUi, PersonDetailsDomain> {
    override fun map(from: PersonDetailsUi) = from.run {
        PersonDetailsDomain(
            known_for_department = known_for_department,
            also_known_as = also_known_as,
            biography = biography,
            birthday = birthday,
            deathDay = deathDay,
            gender = gender,
            id = id,
            name = name,
            popularity = popularity,
            place_of_birth = place_of_birth,
            profile_path = profile_path,
        )
    }
}