package com.fangga.core.data.repository.user

import com.fangga.core.data.base.Resource
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun savePassedOnboardStatus(isPassed: Boolean): Flow<Resource<Unit>>
    suspend fun readPassedOnboardStatus(): Flow<Resource<Boolean>>
}