package com.example.domain.iteractors.movie_iteractors.get_movie_actors_usecase

import com.example.domain.domainModels.person.PersonDomain
import com.example.domain.domainModels.person.PersonsDomain
import kotlinx.coroutines.flow.Flow

interface GetMovieActorsUseCase {
    operator fun invoke(page: Int): Flow<PersonsDomain>
}