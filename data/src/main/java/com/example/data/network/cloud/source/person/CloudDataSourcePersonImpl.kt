package com.example.data.network.cloud.source.person

import com.example.data.dataModel.person.PersonDetailsData
import com.example.data.dataModel.person.PersonsData
import com.example.data.network.cloud.cloudModels.person.PersonDetailsCloud
import com.example.data.network.cloud.cloudModels.person.PersonsCloud
import com.example.data.network.cloud.handler.ResponseHandler
import com.example.data.network.retrofit.api.person.PersonApi
import com.example.domain.assistant.DispatchersProvider
import com.example.domain.base.BaseMapper
import com.example.domain.state.DataRequestState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class CloudDataSourcePersonImpl(
    private val personApi: PersonApi,
    private val mapFromPersonsCloudToData: BaseMapper<PersonsCloud, PersonsData>,
    private val mapFromPersonDetailsCloudToData: BaseMapper<PersonDetailsCloud, PersonDetailsData>,
    private val dispatchersProvider: DispatchersProvider,
    private val responseHandler: ResponseHandler,
) : CloudDataSourcePerson {

    override fun getPersons(page: Int): Flow<PersonsData> = flow {
        emit(personApi.getPersons(page = page))
    }.flowOn(dispatchersProvider.io()).map { it.body() ?: PersonsCloud.unknown() }
        .map(mapFromPersonsCloudToData::map).flowOn(dispatchersProvider.default())

    override suspend fun getPersonDetails(personId: Int): DataRequestState<PersonDetailsData> =
        responseHandler.safeApiMapperCall(mapFromPersonDetailsCloudToData) {
            personApi.getPersonDetails(id = personId)
        }
}