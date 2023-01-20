package com.example.data.dataMappers

import com.example.data.dataModel.movie.MovieData
import com.example.data.dataModel.movie.MoviesData
import com.example.domain.base.BaseMapper
import com.example.domain.domainModels.movie.MovieDomain
import com.example.domain.domainModels.movie.MoviesDomain

class MapFromMoviesDataToDomain(
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