package com.fangga.core.data.datasource

import com.fangga.core.data.source.datastore.UserDataStore
import com.fangga.core.data.source.room.dao.ScanResultDao
import com.fangga.core.data.source.room.entity.ScanResult
import kotlinx.coroutines.flow.Flow

class LocalDataSource(
    private val datastore: UserDataStore,
    private val dao: ScanResultDao
) {
    suspend fun savePassedOnboardStatus(isPassed: Boolean) =
        datastore.setPassedOnboardStatus(isPassed)

    fun readPassedOnboardStatus(): Flow<Boolean> =
        datastore.getPassedOnboardStatus()

    suspend fun insertLatestResultId(resultId: String) =
        datastore.setLatestResultId(resultId)

    fun getLatestResultId(): Flow<String> =
        datastore.getLatestResultId()

    suspend fun insertNewScanResult(scanResult: ScanResult) = dao.insertNewScanResult(scanResult)

    suspend fun getAllScanResults(): List<ScanResult> = dao.getAllScanResults()

    suspend fun getScanResultById(resultId: String): ScanResult? = dao.getScanResultById(resultId)
}