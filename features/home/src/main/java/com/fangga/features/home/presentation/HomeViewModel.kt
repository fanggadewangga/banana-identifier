package com.fangga.features.home.presentation

import com.fangga.core.datasource.datastore.UserDataStore
import com.fangga.core.navigation.NavigationService
import com.fangga.core.presentation.BaseViewModel
import com.fangga.features.home.data.static.Static
import com.fangga.features.home.presentation.event.HomeEvent
import com.fangga.features.home.presentation.state.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userDataStore: UserDataStore,
    private val navigationService: NavigationService
) : BaseViewModel<HomeState, HomeEvent>(HomeState()) {

    private fun loadTipsData() {
        updateUiState { copy(tipsData = Static.tipsAndRecommendation) }
    }

    private fun loadLatestResult() {

    }

    private fun handleScanShortcutClick() {

    }

    private fun handleLatestResultClick(resultId: String) {

    }

    private fun handleLatestResultSwiped(resultId: String) {

    }

    private fun handleTipsItemClicked(resultId: String) {

    }

    override suspend fun handleEvent(event: HomeEvent) {
        when (event) {
            HomeEvent.LoadLatestResult -> loadLatestResult()
            HomeEvent.LoadTipsAndRecommendation -> loadTipsData()
            is HomeEvent.OnLatestResultClicked -> handleLatestResultClick(event.resultId)
            is HomeEvent.OnLatestResultSwiped -> handleLatestResultSwiped(event.resultId)
            HomeEvent.OnScanShortcutClicked -> handleScanShortcutClick()
            is HomeEvent.OnTipsItemClicked -> handleTipsItemClicked(event.tipsId)
        }
    }
}