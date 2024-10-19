package com.fangga.core.di

import com.fangga.core.data.datasource.LocalDataSource
import com.fangga.core.data.repository.scan_result.ScanResultRepository
import com.fangga.core.data.repository.scan_result.ScanResultRepositoryImpl
import com.fangga.core.data.repository.user.UserRepository
import com.fangga.core.data.repository.user.UserRepositoryImpl
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
        localDataSource: LocalDataSource
    ): UserRepository = UserRepositoryImpl(localDataSource)

    @Provides
    @Singleton
    fun provideScanResultRepository(
        localDataSource: LocalDataSource
    ): ScanResultRepository = ScanResultRepositoryImpl(localDataSource)
}