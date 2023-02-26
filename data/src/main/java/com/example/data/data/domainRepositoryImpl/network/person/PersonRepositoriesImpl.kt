package com.example.data.data.domainRepositoryImpl.network.person

import com.example.data.cloud.source.person.CloudDataSourcePerson
import com.example.data.data.models.person.PersonDetailsData
import com.example.data.data.models.person.PersonsData
import com.example.domain.base.BaseMapper
import com.example.domain.models.person.PersonDetailsDomain
import com.example.domain.models.person.PersonsDomain
import com.example.domain.helper.DispatchersProvider
import com.example.domain.repositories.network.person.PersonRepositories
import com.example.domain.state.DataRequestState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PersonRepositoriesImpl @Inject constructor(
    private val dataSourcePerson: CloudDataSourcePerson,
    private val mapFromPersonsDataToDomain: BaseMapper<PersonsData, PersonsDomain>,
    private val mapFromPersonsDetailsDataToDomain: BaseMapper<PersonDetailsData, PersonDetailsDomain>,
    private val dispatchersProvider: DispatchersProvider,
) : PersonRepositories {

    override fun getPersons(page: Int): Flow<PersonsDomain> =
        dataSourcePerson.getPersons(page = page)
            .map(mapFromPersonsDataToDomain::map)
            .flowOn(dispatchersProvider.default())

    override fun getPersonDetails(personId: Int): Flow<PersonDetailsDomain> =
        dataSourcePerson.getAllPersonDetails(personId = personId)
            .map(mapFromPersonsDetailsDataToDomain::map)
            .flowOn(dispatchersProvider.default())

    override fun getSearchPerson(query: String): Flow<PersonsDomain> =
        dataSourcePerson.searchPerson(query = query)
            .map(mapFromPersonsDataToDomain::map)
            .flowOn(dispatchersProvider.default())
}