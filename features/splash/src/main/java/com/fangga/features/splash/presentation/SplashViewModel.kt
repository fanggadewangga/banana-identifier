package com.fangga.features.splash.presentation

import androidx.lifecycle.viewModelScope
import com.fangga.core.datasource.datastore.UserDataStore
import com.fangga.core.navigation.NavigationService
import com.fangga.core.presentation.BaseViewModel
import com.fangga.features.splash.presentation.event.SplashUiEvent
import com.fangga.features.splash.presentation.state.SplashUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val userDataStore: UserDataStore,
    private val navigator: NavigationService,
) : BaseViewModel<SplashUiState, SplashUiEvent>(SplashUiState()) {

    override suspend fun handleEvent(event: SplashUiEvent) {
        when (event) {
            is SplashUiEvent.CheckPassedOnboardStatus -> getPassedOnboardStatus()
            is SplashUiEvent.NavigateToHome -> navigateToHome()
            is SplashUiEvent.NavigateToOnboard -> navigateToOnboard()
        }
    }

    private suspend fun getPassedOnboardStatus() {
        userDataStore.getPassedOnboardStatus().collect { hasPassed ->
            updateUiState { copy(hasPassedOnboard = hasPassed) }
            handleNavigation(hasPassed)
        }
    }

    private fun navigateToHome() {
        viewModelScope.launch {
            navigator.navigateTo("home") {
                popUpTo("splash") { inclusive = true }
            }
        }
    }

    private fun navigateToOnboard() {
        viewModelScope.launch {
            navigator.navigateTo("onboard") {
                popUpTo("splash") { inclusive = true }
            }
        }
    }

    private suspend fun handleNavigation(hasPassedOnboard: Boolean) {
        delay(2000L)
        if (hasPassedOnboard) {
            navigateToHome()
        } else {
            navigateToOnboard()
        }
    }

    init {
        viewModelScope.launch {
            getPassedOnboardStatus()
        }
    }
}