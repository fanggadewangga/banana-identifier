package com.fangga.savedresult.presentation.event

sealed class SavedResultEvent {
    data object LoadSavedResult : SavedResultEvent()
    data object OnBackClicked : SavedResultEvent()
    data class OnSwipeToDelete(val resultId: String) : SavedResultEvent()
    data class NavigateToDetail(val resultId: String) : SavedResultEvent()
}