package com.example.data.storage.mappers

import com.example.data.dataModel.movie.MovieData
import com.example.data.storage.model.movie.MovieStorage
import com.example.domain.base.BaseMapper

class MapFromMovieStorageToData : BaseMapper<MovieStorage, MovieData> {
    override fun map(from: MovieStorage) = from.run {
        MovieData(
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