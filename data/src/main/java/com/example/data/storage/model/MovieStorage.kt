package com.example.data.storage.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.data.storage.db.GenreIdsConverter
import com.example.data.storage.model.MovieStorage.Companion.MOVIES_TABLE

@Entity(tableName = MOVIES_TABLE)
class MovieStorage(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val posterPath: String?,
    val adult: Boolean,
    val overview: String,
    val releaseDate: String?,
    val originalTitle: String?,
    val originalLanguage: String,
    val title: String?,
    val backdropPath: String?,
    val popularity: Double,
    val voteCount: Int,
    val video: Boolean,
    val rating: Double,
    @TypeConverters(GenreIdsConverter::class)
    val genre_ids: List<Int>,
) {
    companion object {
        const val MOVIES_TABLE = "movies_table"
    }
}