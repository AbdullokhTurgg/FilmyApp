package com.example.domain.domainRepositories.network.person

import com.example.domain.domainModels.person.PersonDetailsDomain
import com.example.domain.domainModels.person.PersonsDomain
import com.example.domain.state.DataRequestState
import kotlinx.coroutines.flow.Flow

interface PersonRepositories {
    fun getPersons(page: Int): Flow<PersonsDomain>
    suspend fun getPersonDetails(personId: Int): DataRequestState<PersonDetailsDomain>
}