package com.example.domain.iteractors.movie_iteractors.get_all_popular_usecase

import com.example.domain.domainRepositories.network.movie.MovieRepositories

class GetAllPopularMoviesUseCase(private val repositories: MovieRepositories) {
    suspend fun execute(page: Int) = repositories.getPopularMovie(page = page)

}
