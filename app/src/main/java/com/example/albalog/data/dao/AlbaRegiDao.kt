package com.example.albalog.data.dao

import androidx.room.*
import com.example.albalog.data.entity.AlbaRegiEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AlbaRegiDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAlba(alba: AlbaRegiEntity)

    @Query("SELECT * FROM alba_regi_table")
    fun getAllAlbas(): Flow<List<AlbaRegiEntity>>
}
