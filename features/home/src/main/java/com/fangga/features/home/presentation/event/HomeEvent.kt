package com.fangga.features.home.presentation.event

sealed interface HomeEvent {
    data object OnScanShortcutClicked : HomeEvent
    data class OnTipsItemClicked(val tipsId: String) : HomeEvent
    data class OnLatestResultSwiped(val resultId: String) : HomeEvent
    data class OnLatestResultClicked(val resultId: String) : HomeEvent
    data object LoadLatestResult : HomeEvent
    data object LoadTipsAndRecommendation : HomeEvent
}