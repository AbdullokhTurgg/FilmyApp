package com.example.data.storage.mappers

import com.example.data.dataModel.movie.MovieData
import com.example.data.storage.model.movie.MovieStorage
import com.example.domain.base.BaseMapper

class MapFromMovieDataToStorage : BaseMapper<MovieData, MovieStorage> {
    override fun map(from: MovieData) = from.run {
        MovieStorage(
            id = id,
            posterPath = posterPath,
            adult = adult,
            overview = overview,
            releaseDate = releaseDate,
            originalTitle = originalTitle,
            originalLanguage = originalLanguage,
            title = title,
            backdropPath = backdropPath,
            popularity = popularity,
            voteCount = voteCount,
            video = video,
            rating = rating,
            genre_ids = genre_ids?.map { id -> id },
        )
    }
}