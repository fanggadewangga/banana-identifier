package com.fangga.savedresult.presentation.event

import android.content.Context

sealed class SavedResultEvent {
    data object LoadSavedResult : SavedResultEvent()
    data object OnBackClicked : SavedResultEvent()
    data class OnSwipeToDelete(val context: Context, val resultId: String) : SavedResultEvent()
    data class NavigateToDetail(val resultId: String) : SavedResultEvent()
}