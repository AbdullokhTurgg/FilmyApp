package com.example.data.dataMappers

import com.example.data.dataModel.person.PersonData
import com.example.data.dataModel.person.PersonsData
import com.example.domain.base.BaseMapper
import com.example.domain.domainModels.person.PersonDomain
import com.example.domain.domainModels.person.PersonsDomain

class MapPersonsDataToDomain(
    private val mapFromListPersonDataToDomain: BaseMapper<List<PersonData>, List<PersonDomain>>,
) : BaseMapper<PersonsData, PersonsDomain> {
    override fun map(from: PersonsData) = from.run {
        PersonsDomain(page = page,
            persons = mapFromListPersonDataToDomain.map(persons),
            total_results = total_results,
            total_pages = total_pages)
    }
}