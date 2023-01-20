package com.example.data.network.cloud.cloudMappers.person

import com.example.data.dataModel.person.PersonData
import com.example.data.network.cloud.cloudModels.person.PersonCloud
import com.example.domain.base.BaseMapper

class MapFromListPersonCloudToData(
    private val mapFromPersonCloudToData: BaseMapper<PersonCloud, PersonData>,
) : BaseMapper<List<PersonCloud>, List<PersonData>> {
    override fun map(from: List<PersonCloud>) = from.map {
        mapFromPersonCloudToData.map(it)
    }
}