package com.example.data.storage.movie.movie

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.data.storage.movie.movie.MovieStorage


@Database(entities = [MovieStorage::class], version = 1)
@TypeConverters(GenreIdsConverter::class)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao


    companion object {
        const val MOVIES_DATABASE_NAME = "MoviesDatabase"
    }
}