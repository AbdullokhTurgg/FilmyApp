package com.example.movieappazi.uiMappers

import com.example.domain.assistant.Resource
import com.example.domain.base.BaseMapper
import com.example.domain.domainModels.movie.MovieDomain
import com.example.domain.domainModels.movie.MoviesDomain
import com.example.movieappazi.uiModels.movie.MovieUi
import com.example.movieappazi.uiModels.movie.MoviesUi

class MapFromMoviesDomainToUi(
    private val mapFromListMovieDomainToUi: BaseMapper<List<MovieDomain>, List<MovieUi>>,
) : BaseMapper<MoviesDomain, MoviesUi> {
    override fun map(from: MoviesDomain) = from.run {
        MoviesUi(page = page,
            movies = mapFromListMovieDomainToUi.map(movies),
            totalPage = totalPage,)

    }
}


class ExampleMapper2(
    private val mapFromListMovieDomainToUi: BaseMapper<List<MovieDomain>, List<MovieUi>>,
) : BaseMapper<Resource<MoviesDomain>, MoviesUi> {
    override fun map(from: Resource<MoviesDomain>) = from.run {
        MoviesUi(
            page = data!!.page,
            movies = mapFromListMovieDomainToUi.map(data!!.movies),
            totalPage = data!!.totalPage,
        )

    }
}
