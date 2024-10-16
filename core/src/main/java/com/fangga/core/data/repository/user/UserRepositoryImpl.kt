package com.fangga.core.data.repository.user

import com.fangga.core.data.source.datastore.UserDataStore
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val dataStore: UserDataStore
) : UserRepository {
    override suspend fun savePassedOnboardStatus(isPassed: Boolean) =
        dataStore.setPassedOnboardStatus(isPassed)

    override suspend fun readPassedOnboardStatus(): Flow<Boolean> =
        dataStore.getPassedOnboardStatus()
}