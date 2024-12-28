package com.fangga.core.data.datasource

import android.util.Log
import com.fangga.core.data.base.Resource
import com.fangga.core.data.model.result.ScanResult
import com.fangga.core.data.model.result.ScanResultList
import com.fangga.core.data.source.datastore.UserDataStore
import com.fangga.core.data.source.room.dao.ScanResultDao
import com.fangga.core.data.source.room.entity.ScanResultEntity
import com.fangga.core.utils.Constants.LATEST_RESULT_ID
import com.fangga.core.utils.toScanResult
import com.fangga.core.utils.toScanResultList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

/**
 * **Class:** LocalDataSource
 *
 * **Purpose:**
 * A data source class that manages local data storage and retrieval using
 * DataStore and Room database. This class provides an abstraction layer for
 * accessing local data, making it easier to switch between different storage
 * mechanisms if needed.
 *
 * **Parameters:**
 * - `datastore`: An instance of `UserDataStore` for managing user-related preferences.
 * - `dao`: An instance of `ScanResultDao` for accessing the Room database.
 *
 * **Functionality:**
 * - Provides functions to save and read onboarding status using DataStore.
 * - Provides functions to insert and retrieve scan results using Room database.
 * - Uses `Flow` to emit data and state changes asynchronously.
 * - Uses the `Resource` sealed class to wrap the results of data operations,
 *   indicating loading, success, error, or empty states.
 *
 * **Methods:**
 * - `savePassedOnboardStatus(isPassed: Boolean)`: Saves the onboarding status to DataStore.
 * - `readPassedOnboardStatus()`: Reads the onboarding status from DataStore.
 * - `insertLatestResultId(resultId: String)`: Saves the latest scan result ID to DataStore.
 * - `getLatestScanResult()`: Retrieves the latest scan result from the Room database.
 * - `insertNewScanResult(scanResult: ScanResultEntity)`: Inserts a new scan result into the Room database.
 * - `getAllScanResults()`: Retrieves all scan results from the Room database.
 * - `getScanResultById(resultId: String)`: Retrieves a specific scan result by its ID from the Room database.
 * - `deleteScanResultById(resultId: String)`: Deletes a specific scan result by its ID from the Room database.
 *
 * **Usage:**
 * Use this class to access and manage local data in your application.
 **/

class LocalDataSource(
    private val datastore: UserDataStore,
    private val dao: ScanResultDao
) {
    fun savePassedOnboardStatus(isPassed: Boolean): Flow<Resource<Unit>> = flow {
        emit(Resource.Loading())
        try {
            datastore.setPassedOnboardStatus(isPassed)
            emit(Resource.Success(Unit))
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
        }
    }

    fun readPassedOnboardStatus(): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())
        try {
            val isPassed = datastore.getPassedOnboardStatus().first()
            emit(Resource.Success(isPassed))
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
        }
    }

    fun insertLatestResultId(resultId: String): Flow<Resource<Unit>> = flow {
        emit(Resource.Loading())
        try {
            datastore.setLatestResultId(resultId)
            emit(Resource.Success(Unit))
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
        }
    }


    fun getLatestScanResult(): Flow<Resource<ScanResultList?>> = flow {
        emit(Resource.Loading())
        try {
            val latestScanResult = dao.getScanResultById(LATEST_RESULT_ID)
            if (latestScanResult != null) {
                emit(Resource.Success(latestScanResult.toScanResultList()))
            } else {
                emit(Resource.Success(null))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
        }
    }

    fun insertNewScanResult(scanResult: ScanResultEntity): Flow<Resource<Unit>> = flow {
        emit(Resource.Loading())
        try {
            dao.insertNewScanResult(scanResult)
            emit(Resource.Success(Unit))
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
        }
    }

    fun getAllScanResults(): Flow<Resource<List<ScanResultList>>> = flow {
        emit(Resource.Loading())
        try {
            val results = dao.getAllScanResults()
            Log.d("LocalDataSource", "getAllScanResults: $results")
            if (results.isEmpty()) {
                emit(Resource.Empty())
            } else {
                val mappedResults = results.map { it.toScanResultList() }
                emit(Resource.Success(mappedResults))
            }
        } catch (e: Exception) {
            Log.d("LocalDataSource", "getAllScanResults: ${e.message}")
            emit(Resource.Error(e.message.toString()))
        }
    }

    fun getScanResultById(resultId: String): Flow<Resource<ScanResult?>> = flow {
        emit(Resource.Loading())
        try {
            val result = dao.getScanResultById(resultId)
            if (result != null) {
                emit(Resource.Success(result.toScanResult()))
            } else {
                emit(Resource.Empty())
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
        }
    }

    fun deleteScanResultById(resultId: String): Flow<Resource<Unit>> = flow {
        emit(Resource.Loading())
        try {
            dao.deleteScanResultById(resultId)
            emit(Resource.Success(Unit))
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
        }
    }
}