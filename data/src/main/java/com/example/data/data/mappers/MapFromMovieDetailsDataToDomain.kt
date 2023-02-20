package com.example.data.data.mappers

import com.example.data.data.models.movie.MovieDetailsData
import com.example.data.data.models.movie.MovieGenresData
import com.example.domain.base.BaseMapper
import com.example.domain.models.movie.MovieDetailsDomain
import com.example.domain.models.movie.MovieGenresDomain
import javax.inject.Inject

class MapFromMovieDetailsDataToDomain @Inject constructor(
    private val mapFromListOfMovieGenresDataToDomain: BaseMapper<List<MovieGenresData>, List<MovieGenresDomain>>,
) : BaseMapper<MovieDetailsData, MovieDetailsDomain> {
    override fun map(from: MovieDetailsData) = from.run {
        MovieDetailsDomain(
            backdrop_path = backdrop_path,
            budget = budget,
            genres = mapFromListOfMovieGenresDataToDomain.map(genres),
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