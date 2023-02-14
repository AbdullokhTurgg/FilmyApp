package com.example.data.movie_iteractors.get_all_popular_usecase

import com.example.domain.repositories.network.movie.MovieRepositories

class GetAllPopularMoviesUseCase(private val repositories: MovieRepositories) {
    suspend fun execute(page: Int) = repositories.getPopularMovie(page = page)

}
