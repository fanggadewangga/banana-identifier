package com.fangga.features.home.presentation.event

import android.content.Context
import com.fangga.core.data.model.result.ScanResultList

sealed interface HomeEvent {
    data object OnScanShortcutClicked : HomeEvent
    data class OnTipsItemClicked(val tipsId: String) : HomeEvent
    data class OnLatestResultSwiped(val context: Context, val latestResult: ScanResultList) :
        HomeEvent
    data class OnLatestResultClicked(val resultId: String) : HomeEvent
    data class OnAboutAppClicked(val aboutId: String) : HomeEvent
    data object LoadLatestResult : HomeEvent
}