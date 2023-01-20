package com.example.data.dataMappers

import com.example.data.dataModel.person.PersonData
import com.example.domain.base.BaseMapper
import com.example.domain.domainModels.person.PersonDomain

class MapFromListPersonDataToDomain(
    private val mapFromPersonDataToDomain: BaseMapper<PersonData, PersonDomain>,
) : BaseMapper<List<PersonData>, List<PersonDomain>> {
    override fun map(from: List<PersonData>) = from.map {
        mapFromPersonDataToDomain.map(it)
    }
}