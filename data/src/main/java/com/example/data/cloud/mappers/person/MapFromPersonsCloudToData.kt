package com.example.data.cloud.mappers.person

import com.example.data.cloud.models.person.PersonCloud
import com.example.data.cloud.models.person.PersonsCloud
import com.example.data.data.models.person.PersonData
import com.example.data.data.models.person.PersonsData
import com.example.domain.base.BaseMapper
import javax.inject.Inject

class MapFromPersonsCloudToData @Inject constructor(
    private val mapFromListPersonDataToDomain: BaseMapper<List<PersonCloud>, List<PersonData>>,
) : BaseMapper<PersonsCloud, PersonsData> {
    override fun map(from: PersonsCloud) = from.run {
        PersonsData(page = page,
            persons = mapFromListPersonDataToDomain.map(persons),
            total_results = total_results,
            total_pages = total_pages)
    }
}