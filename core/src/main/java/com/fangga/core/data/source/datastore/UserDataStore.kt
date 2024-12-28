package com.fangga.core.data.source.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * **Class:** UserDataStore
 *
 * **Purpose:**
 * A data store class that manages user-related preferences using Android
 * DataStore. This class provides an abstraction layer for accessing and
 * modifying user preferences, making it easier to manage persistent data.
 *
 * **Parameters:**
 * - `dataStore`: An instance of `DataStore<Preferences>` for accessing the
 *   DataStore.
 *
 * **Functionality:**
 * - Provides functions to set and get the onboarding status.
 * - Provides functions to set and get the latest scan result ID.
 * - Uses `Flow` to emit data changes asynchronously.
 *
 * **Methods:**
 * - `setPassedOnboardStatus(isPassed: Boolean)`: Sets the onboarding status in
 *   DataStore.
 * - `getPassedOnboardStatus()`: Retrieves the onboarding status from DataStore.
 * - `setLatestResultId(resultId: String)`: Sets the latest scan result ID in
 *   DataStore.
 * - `getLatestResultId()`: Retrieves the latest scan result ID from DataStore.
 *
 * **Companion Object:**
 * - `PASSED_ONBOARD_KEY`: A `booleanPreferencesKey` for the onboarding status.
 * - `LATEST_RESULT_KEY`: A `stringPreferencesKey` for the latest scan result ID.
 *
 * **Usage:**
 * Use this class to access and manage user-related preferences in your
 * application.
 */

class UserDataStore(
    private val dataStore: DataStore<Preferences>
) {

    suspend fun setPassedOnboardStatus(isPassed: Boolean) {
        dataStore.edit {
            it[PASSED_ONBOARD_KEY] = isPassed
        }
    }

    fun getPassedOnboardStatus(): Flow<Boolean> {
        return dataStore.data.map {
            it[PASSED_ONBOARD_KEY] ?: false
        }
    }

    suspend fun setLatestResultId(resultId: String) {
        dataStore.edit {
            it[LATEST_RESULT_KEY] = resultId
        }
    }

    fun getLatestResultId(): Flow<String> {
        return dataStore.data.map {
            it[LATEST_RESULT_KEY] ?: ""
        }
    }

    companion object {
        val PASSED_ONBOARD_KEY = booleanPreferencesKey("is_passed_onboard")
        val LATEST_RESULT_KEY = stringPreferencesKey("latest_result")
    }
}