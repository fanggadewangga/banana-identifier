package com.fangga.core.data.source.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fangga.core.data.source.room.entity.ScanResult

@Dao
interface ScanResultDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewScanResult(scanResult: ScanResult)

    @Query("SELECT * FROM ScanResult")
    suspend fun getAllScanResults(): List<ScanResult>

    @Query("SELECT * FROM ScanResult WHERE resultId = :resultId")
    suspend fun getScanResultById(resultId: String): ScanResult?
}