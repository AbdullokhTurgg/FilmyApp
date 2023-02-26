package com.example.data.cloud.source.person

import com.example.data.data.models.person.PersonDetailsData
import com.example.data.data.models.person.PersonsData
import com.example.domain.state.DataRequestState
import kotlinx.coroutines.flow.Flow

interface CloudDataSourcePerson {
    fun getPersons(page: Int): Flow<PersonsData>
    fun getAllPersonDetails(personId: Int): Flow<PersonDetailsData>
    fun searchPerson(query: String): Flow<PersonsData>
}