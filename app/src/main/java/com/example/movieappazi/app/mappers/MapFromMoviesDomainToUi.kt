package com.example.movieappazi.app.mappers

import com.example.domain.base.BaseMapper
import com.example.domain.domainModels.movie.MovieDomain
import com.example.domain.domainModels.movie.MoviesDomain
import com.example.movieappazi.app.models.movie.MovieUi
import com.example.movieappazi.app.models.movie.MoviesUi
import javax.inject.Inject

class MapFromMoviesDomainToUi @Inject constructor(
    private val mapFromListMovieDomainToUi: BaseMapper<List<MovieDomain>, List<MovieUi>>,
) : BaseMapper<MoviesDomain, MoviesUi> {
    override fun map(from: MoviesDomain) = from.run {
        MoviesUi(page = page,
            movies = mapFromListMovieDomainToUi.map(movies),
            totalPage = totalPage,)

    }
}

