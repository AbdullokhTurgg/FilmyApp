package com.example.data.cloud.source.person

import com.example.data.cloud.api.api.person.PersonApi
import com.example.data.cloud.models.person.PersonDetailsCloud
import com.example.data.cloud.models.person.PersonsCloud
import com.example.data.cloud.source.handler.ResponseHandler
import com.example.data.data.models.person.PersonDetailsData
import com.example.data.data.models.person.PersonsData
import com.example.domain.base.BaseMapper
import com.example.domain.helper.DispatchersProvider
import com.example.domain.state.DataRequestState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CloudDataSourcePersonImpl @Inject constructor(
    private val personApi: PersonApi,
    private val mapFromPersonsCloudToData: BaseMapper<PersonsCloud, PersonsData>,
    private val mapFromPersonDetailsCloudToData: BaseMapper<PersonDetailsCloud, PersonDetailsData>,
    private val dispatchersProvider: DispatchersProvider,
) : CloudDataSourcePerson {

    override fun getPersons(page: Int): Flow<PersonsData> =
        flow { emit(personApi.getPersons(page = page)) }
            .flowOn(dispatchersProvider.io())
            .map { it.body() ?: PersonsCloud.unknown() }
            .map(mapFromPersonsCloudToData::map)
            .flowOn(dispatchersProvider.default())

    override fun getAllPersonDetails(personId: Int): Flow<PersonDetailsData> =
        flow { emit(personApi.getPersonDetails(id = personId)) }
            .flowOn(dispatchersProvider.io())
            .map { it.body()!! }
            .map(mapFromPersonDetailsCloudToData::map)
            .flowOn(dispatchersProvider.default())

    override fun searchPerson(query: String): Flow<PersonsData> =
        flow { emit(personApi.getSearchPeople(query = query)) }
            .flowOn(dispatchersProvider.io())
            .map { it.body()!! }
            .map(mapFromPersonsCloudToData::map)
            .flowOn(dispatchersProvider.default())

}