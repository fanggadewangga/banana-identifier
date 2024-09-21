package com.fangga.navigation.di


import com.fangga.core.navigation.NavigationService
import com.fangga.navigation.Navigator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class NavigationModule {
    @Provides
    fun provideNavigationCommander(navigator: Navigator): NavigationService = navigator
}