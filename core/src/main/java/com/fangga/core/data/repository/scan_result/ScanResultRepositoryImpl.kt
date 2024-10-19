package com.fangga.core.data.repository.scan_result

import com.fangga.core.data.base.Resource
import com.fangga.core.data.datasource.LocalDataSource
import com.fangga.core.data.model.result.ScanResult
import com.fangga.core.data.model.result.ScanResultList
import com.fangga.core.data.source.room.entity.ScanResultEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

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