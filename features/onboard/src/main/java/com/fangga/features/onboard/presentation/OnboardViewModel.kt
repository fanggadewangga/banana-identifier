package com.fangga.features.onboard.presentation

import androidx.lifecycle.viewModelScope
import com.fangga.core.data.base.Resource
import com.fangga.core.data.repository.user.UserRepository
import com.fangga.core.navigation.NavigationService
import com.fangga.core.presentation.BaseViewModel
import com.fangga.features.onboard.presentation.event.OnboardEvent
import com.fangga.features.onboard.presentation.state.OnboardState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * **Class:** OnboardViewModel
 *
 * **Purpose:**
 * A ViewModel class that manages the state and logic for the onboarding screen.
 * It handles user interactions, saves the onboarding status, and navigates to
 * the home screen.
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
 * - Extends `BaseViewModel<OnboardState, OnboardEvent>`, which provides a base
 *   implementation for managing UI state and events.
 *
 * **Functionality:**
 * - Handles user interactions such as skipping the onboarding, clicking the
 *   next button, clicking the start button, and changing the onboarding page.
 * - Saves the onboarding status using the `UserRepository`.
 * - Navigates to the home screen using the `NavigationService`.
 * - Manages the UI state using the `OnboardState` data class.
 * - Handles events using the `OnboardEvent` sealed class.
 *
 * **Methods:**
 * - `handleEvent(event: OnboardEvent)`: Handles the different events triggered
 *   by the UI.
 * - `handleSkipOnboard()`: Skips the onboarding process and sets the current
 *   page to the last page.
 * - `handleNextClicked()`: Handles the click on the next button and updates
 *   the current page.
 * - `handleStartClicked()`: Handles the click on the start button, saves the
 *   onboarding status, and navigates to the home screen.
 * - `handlePageChanged(page: Int)`: Handles the change in the onboarding page
 *   and updates the UI state.
 * - `navigateToHome()`: Navigates to the home screen.
 *
 * **Usage:**
 * Use this class as the ViewModel for the onboarding screen in your
 * application.
 */

@HiltViewModel
class OnboardViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val navigator: NavigationService
) : BaseViewModel<OnboardState, OnboardEvent>(OnboardState()) {


    override suspend fun handleEvent(event: OnboardEvent) {
        when (event) {
            is OnboardEvent.SkipOnboard -> handleSkipOnboard()
            is OnboardEvent.OnNextClicked -> handleNextClicked()
            is OnboardEvent.OnStartClicked -> {
                viewModelScope.launch {
                    viewModelScope.launch {
                        userRepository.savePassedOnboardStatus(true).collect { result ->
                            when (result) {
                                is Resource.Empty -> {}
                                is Resource.Error -> {}
                                is Resource.Loading -> {}
                                is Resource.Success -> navigateToHome()
                            }
                        }
                    }
                }
            }

            is OnboardEvent.OnPageChanged -> handlePageChanged(event.page)
        }
    }

    private fun handleNextClicked() {
        val nextPage = uiState.value.currentOnboardPage + 1
        val isLastPage = nextPage == 1
        updateUiState {
            copy(
                currentOnboardPage = if (nextPage <= 1) nextPage else 1,
                isLastPage = isLastPage
            )
        }
    }


    private fun handleSkipOnboard() {
        updateUiState { copy(currentOnboardPage = 1, isLastPage = true) }
    }

    private fun handlePageChanged(page: Int) {
        val isLastPage = page == 1
        updateUiState {
            copy(currentOnboardPage = page, isLastPage = isLastPage)
        }
    }

    private fun navigateToHome() {
        navigator.navigateTo("home")
    }
}