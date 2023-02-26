package com.example.domain.repositories.network.person

import com.example.domain.models.person.PersonDetailsDomain
import com.example.domain.models.person.PersonsDomain
import com.example.domain.state.DataRequestState
import kotlinx.coroutines.flow.Flow

interface PersonRepositories {
    fun getPersons(page: Int): Flow<PersonsDomain>
    fun getPersonDetails(personId: Int): Flow<PersonDetailsDomain>
    fun getSearchPerson(query:String):Flow<PersonsDomain>
}