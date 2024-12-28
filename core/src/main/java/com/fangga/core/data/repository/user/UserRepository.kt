package com.fangga.core.data.repository.user

import com.fangga.core.data.base.Resource
import kotlinx.coroutines.flow.Flow

/**
 * **Interface:** UserRepository
 *
 * **Purpose:**
 * An interface that defines the contract for accessing and managing user-related
 * data. This interface provides an abstraction layer for data operations,
 * allowing different implementations to be used without affecting the rest of
 * the application.
 *
 * **Methods:**
 * - `savePassedOnboardStatus(isPassed: Boolean)`: Saves the onboarding status.
 *   Returns a `Flow` of `Resource<Unit>` to indicate the state of the operation.
 * - `readPassedOnboardStatus()`: Reads the onboarding status. Returns a `Flow`
 *   of `Resource<Boolean>` to indicate the state of the operation and the
 *   result data.
 *
 * **Functionality:**
 * - Defines the operations for managing user-related data.
 * - Uses `Flow` to emit data and state changes asynchronously.
 * - Uses the `Resource` sealed class to wrap the results of data operations,
 *   indicating loading, success, error, or empty states.
 *
 * **Usage:**
 * Use this interface to define the contract for accessing and managing user
 * data in your application.
 */

interface UserRepository {
    suspend fun savePassedOnboardStatus(isPassed: Boolean): Flow<Resource<Unit>>
    suspend fun readPassedOnboardStatus(): Flow<Resource<Boolean>>
}