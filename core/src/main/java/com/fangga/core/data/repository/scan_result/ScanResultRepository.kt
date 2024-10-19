package com.fangga.core.data.repository.scan_result

import com.fangga.core.data.base.Resource
import com.fangga.core.data.model.result.ScanResult
import com.fangga.core.data.model.result.ScanResultList
import com.fangga.core.data.source.room.entity.ScanResultEntity
import kotlinx.coroutines.flow.Flow

interface ScanResultRepository {
    suspend fun insertLatestResultId(resultId: String): Flow<Resource<Unit>>
    suspend fun getLatestScanResult(): Flow<Resource<ScanResultList?>>
    suspend fun insertNewScanResult(scanResult: ScanResultEntity): Flow<Resource<Unit>>
    suspend fun getAllScanResults(): Flow<Resource<List<ScanResultList>>>
    suspend fun getScanResultById(resultId: String): Flow<Resource<ScanResult?>>
    suspend fun deleteScanResultById(resultId: String): Flow<Resource<Unit>>
}