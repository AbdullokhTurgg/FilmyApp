package com.example.movieappazi.uiMappers

import com.example.domain.base.BaseMapper
import com.example.domain.domainModels.movie.MovieDetailsDomain
import com.example.domain.domainModels.movie.MovieGenresDomain
import com.example.movieappazi.uiModels.movie.MovieDetailsUi
import com.example.movieappazi.uiModels.movie.MovieGenresUi

class MapFromMovieDetailsDomainToUi(
    private val mapFromListOfMovieGenresDomainToUi:BaseMapper<List<MovieGenresDomain>, List<MovieGenresUi>>
):BaseMapper<MovieDetailsDomain, MovieDetailsUi> {
    override fun map(from: MovieDetailsDomain) = from.run {
        MovieDetailsUi(
            backdrop_path = backdrop_path,
            budget = budget,
            genres = mapFromListOfMovieGenresDomainToUi.map(genres),
            id = id,
            originalLanguage = originalLanguage,
            originalTitle = originalTitle,
            overview = overview,
            popularity = popularity,
            posterPath = posterPath,
            releaseDate = releaseDate,
            runtime = runtime,
            status = status,
            title = title,
            video = video,
            voteAverage = voteAverage,
            voteCount = voteCount,
        )
    }
}