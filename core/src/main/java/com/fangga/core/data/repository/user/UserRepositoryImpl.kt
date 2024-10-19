package com.fangga.core.data.repository.user

import com.fangga.core.data.base.Resource
import com.fangga.core.data.datasource.LocalDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource
) : UserRepository {
    override suspend fun savePassedOnboardStatus(isPassed: Boolean): Flow<Resource<Unit>> =
        localDataSource.savePassedOnboardStatus(isPassed)

    override suspend fun readPassedOnboardStatus(): Flow<Resource<Boolean>> =
        localDataSource.readPassedOnboardStatus()
}