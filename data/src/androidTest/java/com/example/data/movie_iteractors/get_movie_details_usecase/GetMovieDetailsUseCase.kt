package com.example.data.movie_iteractors.get_movie_details_usecase

interface GetMovieDetailsUseCase {
    suspend operator fun invoke(page:Int)
}