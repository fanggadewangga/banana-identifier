package com.fangga.core.data.source.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fangga.core.data.source.room.entity.ScanResultEntity
import com.fangga.core.utils.Constants.LATEST_RESULT_ID

@Dao
interface ScanResultDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewScanResult(scanResultEntity: ScanResultEntity)

    @Query("SELECT * FROM ScanResultEntity WHERE resultId != :latestResultId")
    suspend fun getAllScanResults(latestResultId: String = LATEST_RESULT_ID): List<ScanResultEntity>

    @Query("SELECT * FROM ScanResultEntity WHERE resultId = :resultId")
    suspend fun getScanResultById(resultId: String): ScanResultEntity?

    @Query("DELETE FROM ScanResultEntity WHERE resultId = :resultId")
    suspend fun deleteScanResultById(resultId: String)
}