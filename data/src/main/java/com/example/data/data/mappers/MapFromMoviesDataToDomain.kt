package com.example.data.data.mappers

import com.example.data.data.models.movie.MovieData
import com.example.data.data.models.movie.MoviesData
import com.example.domain.base.BaseMapper
import com.example.domain.domainModels.movie.MovieDomain
import com.example.domain.domainModels.movie.MoviesDomain
import javax.inject.Inject

class MapFromMoviesDataToDomain @Inject constructor(
    private val mapListOfMovieDataToDomain: BaseMapper<List<MovieData>, List<MovieDomain>>,
) : BaseMapper<MoviesData, MoviesDomain> {
    override fun map(from: MoviesData): MoviesDomain = from.run {
        MoviesDomain(
            page = page,
            movies = mapListOfMovieDataToDomain.map(movies),
            totalPage = totalPage,
        )
    }
}