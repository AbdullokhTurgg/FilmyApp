package com.example.data.storage.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.data.storage.model.MovieStorage


@Database(entities = [MovieStorage::class], version = 1, exportSchema = false)
@TypeConverters(GenreIdsConverter::class)
abstract class MovieDatabase : RoomDatabase() {
    companion object {
        const val MOVIES_DATABASE_NAME = "MoviesDatabase"
    }

    abstract fun movieDao(): MovieDao
}