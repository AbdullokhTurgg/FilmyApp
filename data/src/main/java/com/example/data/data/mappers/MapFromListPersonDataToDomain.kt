package com.example.data.data.mappers

import com.example.data.data.models.person.PersonData
import com.example.domain.base.BaseMapper
import com.example.domain.models.person.PersonDomain

class MapFromListPersonDataToDomain(
    private val mapFromPersonDataToDomain: BaseMapper<PersonData, PersonDomain>,
) : BaseMapper<List<PersonData>, List<PersonDomain>> {
    override fun map(from: List<PersonData>) = from.map {
        mapFromPersonDataToDomain.map(it)
    }
}