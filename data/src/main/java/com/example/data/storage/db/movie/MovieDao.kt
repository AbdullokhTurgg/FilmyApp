package com.example.data.storage.db.movie

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.storage.model.movie.MovieStorage
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM MOVIES_TABLE")
    fun getMoviesList(): Flow<List<MovieStorage>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMovie(movie: MovieStorage)

    @Query("DELETE FROM MOVIES_TABLE WHERE id=:movieId")
    suspend fun deleteMovie(movieId: Int)

    @Query("select * from MOVIES_TABLE where id == :movieId2")
    suspend fun getMovie(movieId2: Int): MovieStorage

}