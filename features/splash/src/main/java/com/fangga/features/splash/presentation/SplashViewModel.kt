package com.fangga.features.splash.presentation

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.fangga.core.datasource.datastore.UserDataStore
import com.fangga.core.navigation.NavigationService
import com.fangga.core.presentation.BaseViewModel
import com.fangga.features.splash.presentation.event.SplashUiEvent
import com.fangga.features.splash.presentation.state.SplashUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val userDataStore: UserDataStore,
    private val navigator: NavigationService,
) : BaseViewModel<SplashUiState, SplashUiEvent>(SplashUiState()) {

    override suspend fun handleEvent(event: SplashUiEvent) {
        when (event) {
            SplashUiEvent.CheckPassedOnboardStatus -> getPassedOnboardStatus()
            SplashUiEvent.NavigateToHome -> navigateToHome()
            SplashUiEvent.NavigateToOnboard -> navigateToOnboard()
        }
    }

    private fun getPassedOnboardStatus() {
        viewModelScope.launch {
            userDataStore.getPassedOnboardStatus().collect { hasPassed ->
                updateUiState {
                    copy(hasPassedOnboard = hasPassed)
                }
            }
        }
    }

    private fun navigateToHome() {
        navigator.navigateTo("home")
    }

    private fun navigateToOnboard() {
        Log.d("Navigate To Onboard", "navigateToOnboard: called")
        navigator.navigateTo("onboard")
    }
}