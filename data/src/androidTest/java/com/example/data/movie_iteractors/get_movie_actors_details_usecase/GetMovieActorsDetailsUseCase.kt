package com.example.data.movie_iteractors.get_movie_actors_details_usecase

import com.example.domain.domainModels.person.PersonDetailsDomain
import com.example.domain.state.DataRequestState
import kotlinx.coroutines.flow.Flow

interface GetMovieActorsDetailsUseCase {
    operator fun invoke(actorsIds: List<Int>): Flow<List<DataRequestState<PersonDetailsDomain>>>
}