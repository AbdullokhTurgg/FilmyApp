package com.example.domain.iteractors.movie_iteractors

import com.example.domain.domainModels.movie.MovieDomain
import com.example.domain.domainRepositories.storage.MovieStorageRepository

class GetFavoriteMovieUseCase constructor(private val repository: MovieStorageRepository) {
    suspend fun execute(id: Int): MovieDomain = repository.getSavedMovies(id = id)
}