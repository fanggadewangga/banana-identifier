package com.fangga.features.onboard.presentation.event

sealed class OnboardEvent {
    data object SkipOnboard : OnboardEvent()
    data object OnNextClicked : OnboardEvent()
    data object OnStartClicked : OnboardEvent()
    data class OnPageChanged(val page: Int): OnboardEvent()
}