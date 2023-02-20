package com.example.movieappazi.app.di

import android.content.Context
import androidx.room.Room
import com.example.data.storage.movie.movie.MovieDao
import com.example.data.storage.movie.movie.MovieDatabase
import com.example.data.storage.tv.room.TvDao
import com.example.data.storage.tv.room.TvDatabase
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
    fun provideTvDatabase(@ApplicationContext context: Context): TvDatabase {
        return Room.databaseBuilder(context, TvDatabase::class.java, TvDatabase.TV_DATABASE_NAME)
            .build()
    }

    @Provides
    fun provideMoviesDao(database: MovieDatabase): MovieDao = database.movieDao()

    @Provides
    fun provideTvDao(database: TvDatabase): TvDao = database.tvDao()
}