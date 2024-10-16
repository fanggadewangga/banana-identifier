package com.fangga.core.data.repository.scan_result

import com.fangga.core.data.source.room.entity.ScanResult

interface ScanResultRepository {
    suspend fun insertLatestResultId(resultId: String)
    suspend fun insertNewScanResult(scanResult: ScanResult)
    suspend fun getAllScanResults(): List<ScanResult>
    suspend fun getScanResultById(resultId: String): ScanResult?
}