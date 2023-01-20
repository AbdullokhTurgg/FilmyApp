package com.example.data.network.cloud.cloudMappers.person

import com.example.data.dataModel.person.PersonData
import com.example.data.dataModel.person.PersonsData
import com.example.data.network.cloud.cloudModels.person.PersonCloud
import com.example.data.network.cloud.cloudModels.person.PersonsCloud
import com.example.domain.base.BaseMapper

class MapFromPersonsCloudToData(
    private val mapFromListPersonDataToDomain: BaseMapper<List<PersonCloud>, List<PersonData>>,
) : BaseMapper<PersonsCloud, PersonsData> {
    override fun map(from: PersonsCloud) = from.run {
        PersonsData(page = page,
            persons = mapFromListPersonDataToDomain.map(persons),
            total_results = total_results,
            total_pages = total_pages)
    }
}