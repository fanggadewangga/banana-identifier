package com.fangga.savedresult.presentation.event

import android.content.Context
import com.fangga.core.data.model.result.ScanResultList

sealed class SavedResultEvent {
    data object LoadSavedResult : SavedResultEvent()
    data object OnBackClicked : SavedResultEvent()
    data class OnSwipeToDelete(val resultId: String) : SavedResultEvent()
    data class NavigateToDetail(val context: Context, val item: ScanResultList) : SavedResultEvent()
}