package com.example.data.network.cloud.cloudMappers.person

import com.example.data.dataModel.person.PersonDetailsData
import com.example.data.network.cloud.cloudModels.person.PersonDetailsCloud
import com.example.domain.base.BaseMapper

class MapFromPersonDetailsCloudToData : BaseMapper<PersonDetailsCloud, PersonDetailsData> {
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