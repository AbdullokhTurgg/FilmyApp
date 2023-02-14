package com.example.data.cloud.mappers.person

import com.example.data.cloud.models.person.PersonDetailsCloud
import com.example.data.data.models.person.PersonDetailsData
import com.example.domain.base.BaseMapper
import javax.inject.Inject

class MapFromPersonDetailsCloudToData @Inject constructor() : BaseMapper<PersonDetailsCloud, PersonDetailsData> {
    override fun map(from: PersonDetailsCloud) = from.run {
        PersonDetailsData(
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