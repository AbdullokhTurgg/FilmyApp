package com.example.data.storage.tv.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.storage.tv.models.TvStorage
import kotlinx.coroutines.flow.Flow

@Dao
interface TvDao {

    @Query("SELECT * FROM seriesTable")
    fun getTvStorage(): Flow<List<TvStorage>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveTv(tv: TvStorage)

    @Query("DELETE FROM seriesTable WHERE id =:id")
    fun deleteTVFromSaveStorage(id: Int)
}