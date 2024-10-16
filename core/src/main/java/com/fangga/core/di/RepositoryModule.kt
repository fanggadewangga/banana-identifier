package com.fangga.core.di

import com.fangga.core.data.repository.scan_result.ScanResultRepository
import com.fangga.core.data.repository.scan_result.ScanResultRepositoryImpl
import com.fangga.core.data.repository.user.UserRepository
import com.fangga.core.data.repository.user.UserRepositoryImpl
import com.fangga.core.data.source.datastore.UserDataStore
import com.fangga.core.data.source.room.database.BananaIdentifierDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideUserRepository(
        dataStore: UserDataStore
    ): UserRepository = UserRepositoryImpl(dataStore)

    @Provides
    @Singleton
    fun provideScanResultRepository(
        dataStore: UserDataStore,
        db: BananaIdentifierDb
    ): ScanResultRepository = ScanResultRepositoryImpl(dataStore, db.scanResultDao)
}