package com.fangga.core.datasource.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

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