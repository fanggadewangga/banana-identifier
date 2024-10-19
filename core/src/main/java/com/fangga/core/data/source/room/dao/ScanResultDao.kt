package com.fangga.core.data.source.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fangga.core.data.source.room.entity.ScanResultEntity

@Dao
interface ScanResultDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewScanResult(scanResultEntity: ScanResultEntity)

    @Query("SELECT * FROM ScanResultEntity")
    suspend fun getAllScanResults(): List<ScanResultEntity>

    @Query("SELECT * FROM ScanResultEntity WHERE resultId = :resultId")
    suspend fun getScanResultById(resultId: String): ScanResultEntity?

    @Query("DELETE FROM ScanResultEntity WHERE resultId = :resultId")
    suspend fun deleteScanResultById(resultId: String)
}