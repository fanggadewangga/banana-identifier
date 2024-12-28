package com.fangga.core.data.repository.scan_result

import com.fangga.core.data.base.Resource
import com.fangga.core.data.datasource.LocalDataSource
import com.fangga.core.data.model.result.ScanResult
import com.fangga.core.data.model.result.ScanResultList
import com.fangga.core.data.source.room.entity.ScanResultEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * **Class:** ScanResultRepositoryImpl
 *
 * **Purpose:**
 * An implementation of the `ScanResultRepository` interface that uses a
 * `LocalDataSource` to access and manage scan result data. This class provides
 * a concrete implementation of the data access layer, using local storage as
 * the data source.
 *
 * **Parameters:**
 * - `localDataSource`: An instance of `LocalDataSource` for accessing local data.
 *
 * **Functionality:**
 * - Implements the methods defined in the `ScanResultRepository` interface.
 * - Delegates the data operations to the `LocalDataSource`.
 * - Uses `Flow` to emit data and state changes asynchronously.
 * - Uses the `Resource` sealed class to wrap the results of data operations,
 *   indicating loading, success, error, or empty states.
 *
 * **Methods:**
 * - `insertLatestResultId(resultId: String)`: Inserts the latest scan result ID
 *   by delegating to the `localDataSource`.
 * - `getLatestScanResult()`: Retrieves the latest scan result by delegating to
 *   the `localDataSource`.
 * - `insertNewScanResult(scanResult: ScanResultEntity)`: Inserts a new scan
 *   result by delegating to the `localDataSource`.
 * - `getAllScanResults()`: Retrieves all scan results by delegating to the
 *   `localDataSource`.
 * - `getScanResultById(resultId: String)`: Retrieves a specific scan result by
 *   its ID by delegating to the `localDataSource`.
 * - `deleteScanResultById(resultId: String)`: Deletes a specific scan result by
 *   its ID by delegating to the `localDataSource`.
 *
 * **Usage:**
 * Use this class as a concrete implementation of the `ScanResultRepository`
 * interface in your application.
 */

class ScanResultRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource
) : ScanResultRepository {
    override suspend fun insertLatestResultId(resultId: String): Flow<Resource<Unit>> =
        localDataSource.insertLatestResultId(resultId)

    override suspend fun getLatestScanResult(): Flow<Resource<ScanResultList?>> =
        localDataSource.getLatestScanResult()

    override suspend fun insertNewScanResult(scanResult: ScanResultEntity): Flow<Resource<Unit>> =
        localDataSource.insertNewScanResult(scanResult)

    override suspend fun getAllScanResults(): Flow<Resource<List<ScanResultList>>> =
        localDataSource.getAllScanResults()

    override suspend fun getScanResultById(resultId: String): Flow<Resource<ScanResult?>> =
        localDataSource.getScanResultById(resultId)

    override suspend fun deleteScanResultById(resultId: String): Flow<Resource<Unit>> =
        localDataSource.deleteScanResultById(resultId)
}