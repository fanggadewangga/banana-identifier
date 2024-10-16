package com.fangga.core.di

import com.fangga.core.data.datasource.LocalDataSource
import com.fangga.core.data.source.datastore.UserDataStore
import com.fangga.core.data.source.room.database.BananaIdentifierDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatasourceModule {
    @Provides
    @Singleton
    fun provideLocalDataSource(
        db: BananaIdentifierDb,
        datastore: UserDataStore
    ) = LocalDataSource(datastore, db.scanResultDao)
}