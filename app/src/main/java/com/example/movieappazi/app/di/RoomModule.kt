package com.example.movieappazi.app.di

import android.content.Context
import androidx.room.Room
import com.example.data.storage.db.movie.MovieDao
import com.example.data.storage.db.movie.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    fun provideMovieDatabase(@ApplicationContext context: Context): MovieDatabase {
        return Room.databaseBuilder(context,
            MovieDatabase::class.java,
            MovieDatabase.MOVIES_DATABASE_NAME).build()
    }

    @Provides
    fun provideAllNewsDao(database: MovieDatabase): MovieDao = database.movieDao()
}