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

class LocalDataSource(
    private val datastore: UserDataStore,
    private val dao: ScanResultDao
) {
    suspend fun savePassedOnboardStatus(isPassed: Boolean): Flow<Resource<Unit>> = flow {
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

    suspend fun insertLatestResultId(resultId: String): Flow<Resource<Unit>> = flow {
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

    suspend fun insertNewScanResult(scanResult: ScanResultEntity): Flow<Resource<Unit>> = flow {
        emit(Resource.Loading())
        try {
            dao.insertNewScanResult(scanResult)
            emit(Resource.Success(Unit))
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
        }
    }

    suspend fun getAllScanResults(): Flow<Resource<List<ScanResultList>>> = flow {
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

    suspend fun getScanResultById(resultId: String): Flow<Resource<ScanResult?>> = flow {
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

    suspend fun deleteScanResultById(resultId: String): Flow<Resource<Unit>> = flow {
        emit(Resource.Loading())
        try {
            dao.deleteScanResultById(resultId)
            emit(Resource.Success(Unit))
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
        }
    }
}