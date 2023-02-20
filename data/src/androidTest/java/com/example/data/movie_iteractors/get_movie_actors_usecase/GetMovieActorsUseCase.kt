package com.example.data.movie_iteractors.get_movie_actors_usecase

import com.example.domain.models.person.PersonsDomain
import kotlinx.coroutines.flow.Flow

interface GetMovieActorsUseCase {
    operator fun invoke(page: Int): Flow<PersonsDomain>
}