package com.example.data.data.mappers

import com.example.data.data.models.person.PersonData
import com.example.data.data.models.person.PersonsData
import com.example.domain.base.BaseMapper
import com.example.domain.domainModels.person.PersonDomain
import com.example.domain.domainModels.person.PersonsDomain
import javax.inject.Inject

class MapPersonsDataToDomain @Inject constructor(
    private val mapFromListPersonDataToDomain: BaseMapper<List<PersonData>, List<PersonDomain>>,
) : BaseMapper<PersonsData, PersonsDomain> {
    override fun map(from: PersonsData) = from.run {
        PersonsDomain(page = page,
            persons = mapFromListPersonDataToDomain.map(persons),
            total_results = total_results,
            total_pages = total_pages)
    }
}