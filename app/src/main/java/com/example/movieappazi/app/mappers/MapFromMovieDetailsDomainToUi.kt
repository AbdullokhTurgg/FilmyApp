package com.example.movieappazi.app.mappers

import com.example.domain.base.BaseMapper
import com.example.domain.domainModels.movie.MovieDetailsDomain
import com.example.domain.domainModels.movie.MovieGenresDomain
import com.example.movieappazi.app.models.movie.MovieDetailsUi
import com.example.movieappazi.app.models.movie.MovieGenresUi
import javax.inject.Inject

class MapFromMovieDetailsDomainToUi @Inject constructor(
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