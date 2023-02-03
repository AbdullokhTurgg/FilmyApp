package com.example.data.dataMappers

import com.example.data.dataModel.person.PersonDetailsData
import com.example.domain.base.BaseMapper
import com.example.domain.domainModels.person.PersonDetailsDomain

class MapFromListPersonDetailsDomainToData(
    private val mapFromPersonDetailsDomainToData: BaseMapper<PersonDetailsDomain, PersonDetailsData>,
) : BaseMapper<List<PersonDetailsDomain>, List<PersonDetailsData>> {
    override fun map(from: List<PersonDetailsDomain>) = from.map {
        mapFromPersonDetailsDomainToData.map(it)
    }
}