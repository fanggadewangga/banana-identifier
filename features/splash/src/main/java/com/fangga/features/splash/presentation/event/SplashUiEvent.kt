package com.fangga.features.splash.presentation.event

sealed class SplashUiEvent {
    data object CheckPassedOnboardStatus : SplashUiEvent()
    data object NavigateToHome: SplashUiEvent()
    data object NavigateToOnboard: SplashUiEvent()
}