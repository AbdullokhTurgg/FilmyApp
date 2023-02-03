package com.example.data.storage.mappers

import com.example.data.dataModel.movie.MovieData
import com.example.data.storage.model.movie.MovieStorage
import com.example.domain.base.BaseMapper

class MapListOfStorageMoviesToData(
    private val mapper: BaseMapper<MovieStorage, MovieData>,
) : BaseMapper<List<MovieStorage>, List<MovieData>> {
    override fun map(from: List<MovieStorage>) = from.map {
        mapper.map(it)
    }
}