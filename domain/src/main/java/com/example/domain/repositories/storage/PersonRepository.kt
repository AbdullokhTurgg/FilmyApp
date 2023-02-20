package com.example.domain.repositories.storage

import com.example.domain.models.person.PersonDetailsDomain
import kotlinx.coroutines.flow.Flow

interface PersonRepository {

    suspend fun savePersonToDatabase(person: PersonDetailsDomain)

    suspend fun deletePersonFromDatabase(person: PersonDetailsDomain)

    fun getAllPersonsFromDatabase(): Flow<List<PersonDetailsDomain>>

    suspend fun getSavedPersons(id: Int): PersonDetailsDomain
}