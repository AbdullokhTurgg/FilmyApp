package com.example.data.storage.tv.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(tableName = "seriesTable")
class TvStorage(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val backdropPath: String?,
    val firstAirDate: String,
    @TypeConverters(com.example.data.storage.tv.TypeConverter::class)
    val genreIds: List<Int>,
    val name: String,
    val originalLanguage: String,
    val originalName: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String?,
    val voteAverage: Double,
    val voteCount: Int,
)
