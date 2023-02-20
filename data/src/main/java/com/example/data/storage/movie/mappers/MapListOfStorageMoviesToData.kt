package com.example.data.storage.movie.mappers

import com.example.data.data.models.movie.MovieData
import com.example.data.storage.movie.movie.MovieStorage
import com.example.domain.base.BaseMapper

class MapListOfStorageMoviesToData(
    private val mapper: BaseMapper<MovieStorage, MovieData>,
) : BaseMapper<List<MovieStorage>, List<MovieData>> {
    override fun map(from: List<MovieStorage>) = from.map {
        mapper.map(it)
    }
}