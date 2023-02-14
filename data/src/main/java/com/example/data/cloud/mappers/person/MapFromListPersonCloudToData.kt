package com.example.data.cloud.mappers.person

import com.example.data.cloud.models.person.PersonCloud
import com.example.data.data.models.person.PersonData
import com.example.domain.base.BaseMapper

class MapFromListPersonCloudToData(
    private val mapFromPersonCloudToData: BaseMapper<PersonCloud, PersonData>,
) : BaseMapper<List<PersonCloud>, List<PersonData>> {
    override fun map(from: List<PersonCloud>) = from.map {
        mapFromPersonCloudToData.map(it)
    }
}