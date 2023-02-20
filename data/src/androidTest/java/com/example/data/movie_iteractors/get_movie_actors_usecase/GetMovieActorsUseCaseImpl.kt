package com.example.data.movie_iteractors.get_movie_actors_usecase

import com.example.domain.helper.DispatchersProvider
import com.example.domain.models.person.PersonsDomain
import com.example.domain.repositories.network.person.PersonRepositories
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetMovieActorsUseCaseImpl(
    private val repository: PersonRepositories,
    private val dispatchersProvider: DispatchersProvider,
) : GetMovieActorsUseCase {

    override fun invoke(page: Int): Flow<PersonsDomain> = flow {}
//        emit(getActors(page = page))}.flowOn(dispatchersProvider.io())

//    suspend fun getActors(page: Int): PersonsDomain = repository.getPersons(page = page)

}