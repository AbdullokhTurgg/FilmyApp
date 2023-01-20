package com.example.data.domainRepositoryImpl.network.person

import com.example.data.dataModel.person.PersonDetailsData
import com.example.data.dataModel.person.PersonsData
import com.example.data.network.cloud.source.person.CloudDataSourcePerson
import com.example.domain.assistant.DispatchersProvider
import com.example.domain.base.BaseMapper
import com.example.domain.domainModels.person.PersonDetailsDomain
import com.example.domain.domainModels.person.PersonsDomain
import com.example.domain.domainRepositories.network.person.PersonRepositories
import com.example.domain.state.DataRequestState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class PersonRepositoriesImpl(
    private val dataSourcePerson: CloudDataSourcePerson,
    private val mapFromPersonsDataToDomain: BaseMapper<PersonsData, PersonsDomain>,
    private val mapFromPersonsDetailsDataToDomain: BaseMapper<PersonDetailsData, PersonDetailsDomain>,
    private val dispatchersProvider: DispatchersProvider,
) : PersonRepositories {

    override fun getPersons(page: Int): Flow<PersonsDomain> =
        dataSourcePerson.getPersons(page = page).map(mapFromPersonsDataToDomain::map)
            .flowOn(dispatchersProvider.default())


    override suspend fun getPersonDetails(personId: Int): DataRequestState<PersonDetailsDomain> =
        dataSourcePerson.getPersonDetails(personId = personId)
            .map(mapFromPersonsDetailsDataToDomain)
}