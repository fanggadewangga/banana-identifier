package com.fangga.core.data.repository.user

import com.fangga.core.data.base.Resource
import com.fangga.core.data.datasource.LocalDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * **Class:** UserRepositoryImpl
 *
 * **Purpose:**
 * An implementation of the `UserRepository` interface that uses a
 * `LocalDataSource` to access and manage user-related data. This class provides
 * a concrete implementation of the data access layer, using local storage as
 * the data source.
 *
 * **Parameters:**
 * - `localDataSource`: An instance of `LocalDataSource` for accessing local data.
 *
 * **Functionality:**
 * - Implements the methods defined in the `UserRepository` interface.
 * - Delegates the data operations to the `LocalDataSource`.
 * - Uses `Flow` to emit data and state changes asynchronously.
 * - Uses the `Resource` sealed class to wrap the results of data operations,
 *   indicating loading, success, or error states.
 *
 * **Methods:**
 * - `savePassedOnboardStatus(isPassed: Boolean)`: Saves the onboarding status
 *   by delegating to the `localDataSource`.
 * - `readPassedOnboardStatus()`: Reads the onboarding status by delegating to
 *   the `localDataSource`.
 *
 * **Usage:**
 * Use this class as a concrete implementation of the `UserRepository`
 * interface in your application.
 */

class UserRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource
) : UserRepository {
    override suspend fun savePassedOnboardStatus(isPassed: Boolean): Flow<Resource<Unit>> =
        localDataSource.savePassedOnboardStatus(isPassed)

    override suspend fun readPassedOnboardStatus(): Flow<Resource<Boolean>> =
        localDataSource.readPassedOnboardStatus()
}