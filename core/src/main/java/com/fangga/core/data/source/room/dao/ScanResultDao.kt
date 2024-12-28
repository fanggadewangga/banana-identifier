package com.fangga.core.data.source.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fangga.core.data.source.room.entity.ScanResultEntity
import com.fangga.core.utils.Constants.LATEST_RESULT_ID

/**
 * **Interface:** ScanResultDao
 *
 * **Purpose:**
 * A Data Access Object (DAO) interface for accessing and managing scan result
 * data in the Room database. This interface defines the methods for interacting
 * with the `ScanResultEntity` table.
 *
 * **Annotations:**
 * - `@Dao`: Indicates that this interface is a Room DAO.
 *
 * **Methods:**
 * - `insertNewScanResult(scanResultEntity: ScanResultEntity)`: Inserts a new
 *   scan result into the database. If a conflict occurs, it replaces the
 *   existing entry.
 * - `getAllScanResults(latestResultId: String = LATEST_RESULT_ID)`: Retrieves all
 *   scan results from the database, excluding the one with the `latestResultId`.
 * - `getScanResultById(resultId: String)`: Retrieves a specific scan result by
 *   its ID from the database.
 * - `deleteScanResultById(resultId: String)`: Deletes a specific scan result by
 *   its ID from the database.
 *
 * **Functionality:**
 * - Provides an abstraction layer for accessing the Room database.
 * - Uses Room annotations to define SQL queries and database operations.
 * - Uses Kotlin coroutines for asynchronous database operations.
 *
 * **Usage:**
 * Use this interface to access and manage scan result data in your Room
 * database
 *
 **/

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