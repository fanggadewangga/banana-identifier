package com.fangga.features.splash.presentation

import androidx.lifecycle.viewModelScope
import com.fangga.core.data.base.Resource
import com.fangga.core.data.repository.user.UserRepository
import com.fangga.core.navigation.NavigationService
import com.fangga.core.presentation.BaseViewModel
import com.fangga.features.splash.presentation.event.SplashUiEvent
import com.fangga.features.splash.presentation.state.SplashUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * **Class:** SplashViewModel
 *
 * **Purpose:**
 * A ViewModel class that manages the state and logic for the splash screen. It
 * checks if the user has passed the onboarding screen and navigates to the
 * appropriate screen.
 *
 * **Annotations:**
 * - `@HiltViewModel`: Indicates that this class is a Hilt ViewModel, which
 *   enables dependency injection.
 *
 * **Parameters:**
 * - `userRepository`: An instance of `UserRepository` for accessing user-related
 *   data.
 * - `navigator`: An instance of `NavigationService` for navigating between
 *   screens.
 *
 * **Inheritance:**
 * - Extends `BaseViewModel<SplashUiState, SplashUiEvent>`, which provides a
 *   base implementation for managing UI state and events.
 *
 * **Functionality:**
 * - Checks if the user has passed the onboarding screen by reading the
 *   onboarding status from the `UserRepository`.
 * - Navigates to the home screen if the user has passed the onboarding screen.
 * - Navigates to the onboarding screen if the user has not passed the
 *   onboarding screen.
 * - Manages the UI state using the `SplashUiState` data class.
 * - Handles events using the `SplashUiEvent` sealed class.
 *
 * **Methods:**
 * - `handleEvent(event: SplashUiEvent)`: Handles the different events
 *   triggered by the UI.
 * - `getPassedOnboardStatus()`: Reads the onboarding status from the
 *   `UserRepository` and updates the UI state.
 * - `navigateToHome()`: Navigates to the home screen.
 * - `navigateToOnboard()`: Navigates to the onboarding screen.
 * - `handleNavigation(hasPassedOnboard: Boolean)`: Delays navigation and
 *   navigates to the appropriate screen based on the onboarding status.
 *
 * **Usage:**
 * Use this class as the ViewModel for the splash screen in your application.
 */

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val userRepository: UserRepository,
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
        userRepository.readPassedOnboardStatus().collectLatest { result ->
            when (result) {
                is Resource.Success -> {
                    val hasPassed = result.data!!
                    updateUiState { copy(hasPassedOnboard = hasPassed) }
                    handleNavigation(hasPassed)
                }

                is Resource.Empty -> {}
                is Resource.Error -> {}
                is Resource.Loading -> {}
            }
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
