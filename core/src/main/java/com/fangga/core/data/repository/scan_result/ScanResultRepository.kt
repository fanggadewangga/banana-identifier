package com.fangga.core.data.repository.scan_result

import com.fangga.core.data.base.Resource
import com.fangga.core.data.model.result.ScanResult
import com.fangga.core.data.model.result.ScanResultList
import com.fangga.core.data.source.room.entity.ScanResultEntity
import kotlinx.coroutines.flow.Flow

/**
 * **Interface:** ScanResultRepository
 *
 * **Purpose:**
 * An interface that defines the contract for accessing and managing scan result
 * data. This interface provides an abstraction layer for data operations,
 * allowing different implementations to be used without affecting the rest of
 * the application.
 *
 * **Methods:**
 * - `insertLatestResultId(resultId: String)`: Inserts the latest scan result ID.
 *   Returns a `Flow` of `Resource<Unit>` to indicate the state of the operation.
 * - `getLatestScanResult()`: Retrieves the latest scan result. Returns a `Flow`
 *   of `Resource<ScanResultList?>` to indicate the state of the operation and
 *   the result data.
 * - `insertNewScanResult(scanResult: ScanResultEntity)`: Inserts a new scan result.
 *   Returns a `Flow` of `Resource<Unit>` to indicate the state of the operation.
 * - `getAllScanResults()`: Retrieves all scan results. Returns a `Flow` of
 *   `Resource<List<ScanResultList>>` to indicate the state of the operation and
 *   the list of results.
 * - `getScanResultById(resultId: String)`: Retrieves a specific scan result by its ID.
 *   Returns a `Flow` of `Resource<ScanResult?>` to indicate the state of the
 *   operation and the result data.
 * - `deleteScanResultById(resultId: String)`: Deletes a specific scan result by its ID.
 *   Returns a `Flow` of `Resource<Unit>` to indicate the state of the operation.
 *
 * **Functionality:**
 * - Defines the operations for managing scan result data.
 * - Uses `Flow` to emit data and state changes asynchronously.
 * - Uses the `Resource` sealed class to wrap the results of data operations,
 *   indicating loading, success, error, or empty states.
 *
 * **Usage:**
 * Use this interface to define the contract for accessing and managing scan
 * result data in your application.
 */

interface ScanResultRepository {
    suspend fun insertLatestResultId(resultId: String): Flow<Resource<Unit>>
    suspend fun getLatestScanResult(): Flow<Resource<ScanResultList?>>
    suspend fun insertNewScanResult(scanResult: ScanResultEntity): Flow<Resource<Unit>>
    suspend fun getAllScanResults(): Flow<Resource<List<ScanResultList>>>
    suspend fun getScanResultById(resultId: String): Flow<Resource<ScanResult?>>
    suspend fun deleteScanResultById(resultId: String): Flow<Resource<Unit>>
}