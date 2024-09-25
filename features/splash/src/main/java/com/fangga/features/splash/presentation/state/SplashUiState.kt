package com.fangga.features.splash.presentation.state

import androidx.compose.runtime.Immutable

@Immutable
data class SplashUiState(
    val hasPassedOnboard: Boolean = false
)