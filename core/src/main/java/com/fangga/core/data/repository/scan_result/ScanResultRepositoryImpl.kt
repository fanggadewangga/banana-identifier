package com.fangga.core.data.repository.scan_result

import com.fangga.core.data.source.datastore.UserDataStore
import com.fangga.core.data.source.room.dao.ScanResultDao
import com.fangga.core.data.source.room.entity.ScanResult
import javax.inject.Inject

class ScanResultRepositoryImpl @Inject constructor(
    private val dataStore: UserDataStore,
    private val dao: ScanResultDao
) : ScanResultRepository {
    override suspend fun insertLatestResultId(resultId: String) =
        dataStore.setLatestResultId(resultId)

    override suspend fun insertNewScanResult(scanResult: ScanResult) =
        dao.insertNewScanResult(scanResult)

    override suspend fun getAllScanResults(): List<ScanResult> = dao.getAllScanResults()

    override suspend fun getScanResultById(resultId: String): ScanResult? =
        dao.getScanResultById(resultId)
}