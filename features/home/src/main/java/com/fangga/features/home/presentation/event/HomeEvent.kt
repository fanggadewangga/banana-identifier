package com.fangga.features.home.presentation.event

import android.content.Context
import com.fangga.core.data.model.result.ScanResultList

sealed interface HomeEvent {
    data object OnScanShortcutClicked : HomeEvent
    data class OnTipsItemClicked(val tipsId: String) : HomeEvent
    data class OnLatestResultSwiped(val latestResult: ScanResultList) : HomeEvent
    data class OnLatestResultClicked(val context: Context) : HomeEvent
    data class OnAboutAppClicked(val aboutId: String) : HomeEvent
    data object LoadLatestResult : HomeEvent
    data object HideToast : HomeEvent
}