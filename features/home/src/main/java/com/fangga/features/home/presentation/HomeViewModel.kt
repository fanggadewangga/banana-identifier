package com.fangga.features.home.presentation

import android.util.Log
import com.fangga.core.data.source.datastore.UserDataStore
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

    private fun loadAboutData() {
        updateUiState { copy(aboutData = Static.aboutApp) }
    }

    private fun loadLatestResult() {

    }

    private fun handleScanShortcutClick() {

    }

    private fun handleLatestResultClick(resultId: String) {
        navigationService.navigateTo("saved_result")
        Log.d("HomeViewModel", "handleLatestResultClick: $resultId")

    }

    private fun handleLatestResultSwiped(resultId: String) {

    }

    private fun handleTipsItemClicked(tipsId: String) {
        navigationService.navigateTo("tips/${tipsId}") {
            launchSingleTop = true
            restoreState = true
        }
    }

    private fun handleAboutAppClicked(aboutId: String) {
        when (aboutId) {
            // TODO : Handle Rating Navigation
            "privacy_policy" -> navigationService.navigateTo("privacy_and_policy")
            "terms_and_conditions" -> navigationService.navigateTo("terms_and_condition")
            "rating" -> {}
        }
    }

    override suspend fun handleEvent(event: HomeEvent) {
        when (event) {
            HomeEvent.LoadLatestResult -> loadLatestResult()
            HomeEvent.OnScanShortcutClicked -> handleScanShortcutClick()
            is HomeEvent.OnLatestResultClicked -> handleLatestResultClick(event.resultId)
            is HomeEvent.OnLatestResultSwiped -> handleLatestResultSwiped(event.resultId)
            is HomeEvent.OnTipsItemClicked -> handleTipsItemClicked(event.tipsId)
            is HomeEvent.OnAboutAppClicked -> handleAboutAppClicked(event.aboutId)
        }
    }
}