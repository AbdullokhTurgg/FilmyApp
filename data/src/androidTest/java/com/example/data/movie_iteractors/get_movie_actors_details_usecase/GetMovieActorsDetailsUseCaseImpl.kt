package com.example.data.movie_iteractors.get_movie_actors_details_usecase

import com.example.domain.helper.DispatchersProvider
import com.example.domain.models.person.PersonDetailsDomain
import com.example.domain.repositories.network.person.PersonRepositories
import com.example.domain.state.DataRequestState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetMovieActorsDetailsUseCaseImpl constructor(
    private val repository: PersonRepositories,
    private val dispatchersProvider: DispatchersProvider,
) : GetMovieActorsDetailsUseCase {

    override fun invoke(actorsIds: List<Int>): Flow<List<DataRequestState<PersonDetailsDomain>>> =
        flow { emit(getActors(actorsIds)) }.flowOn(dispatchersProvider.io())

    suspend fun getActors(actorsId: List<Int>?): List<DataRequestState<PersonDetailsDomain>> {
        if (actorsId == null) return listOf(DataRequestState.Error(NullPointerException("parametrs is null")))
        return actorsId.map { id -> repository.getPersonDetails(id) }
    }
}