package com.example.data.dataMappers

import com.example.data.dataModel.movie.MovieDetailsData
import com.example.data.dataModel.movie.MovieGenresData
import com.example.domain.base.BaseMapper
import com.example.domain.domainModels.movie.MovieDetailsDomain
import com.example.domain.domainModels.movie.MovieGenresDomain

class MapFromMovieDetailsDataToDomain(
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