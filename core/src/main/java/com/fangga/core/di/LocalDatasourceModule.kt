package com.fangga.core.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.room.Room
import com.fangga.core.data.source.datastore.UserDataStore
import com.fangga.core.data.source.room.database.BananaIdentifierDb
import com.fangga.core.utils.Constants
import com.fangga.core.utils.Constants.ROOM_DB_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDatasourceModule {
    @Provides
    @Singleton
    fun provideDataStore(
        @ApplicationContext context: Context
    ): DataStore<Preferences> = PreferenceDataStoreFactory.create(
        corruptionHandler = ReplaceFileCorruptionHandler(
            produceNewData = { emptyPreferences() }
        ),
        migrations = listOf(SharedPreferencesMigration(context, Constants.DATASTORE_NAME)),
        scope = CoroutineScope(Dispatchers.IO + SupervisorJob()),
        produceFile = { context.preferencesDataStoreFile(Constants.DATASTORE_NAME) }
    )

    @Provides
    @Singleton
    fun provideUserDataStore(
        dataStore: DataStore<Preferences>
    ): UserDataStore = UserDataStore(dataStore)

    @Provides
    @Singleton
    fun provideRoomDatabase(@ApplicationContext context: Context): BananaIdentifierDb {
        return Room.databaseBuilder(
            context,
            BananaIdentifierDb::class.java,
            ROOM_DB_NAME
        ).build()
    }
}