package com.fangga.features.onboard.presentation

import androidx.lifecycle.viewModelScope
import com.fangga.core.data.source.datastore.UserDataStore
import com.fangga.core.navigation.NavigationService
import com.fangga.core.presentation.BaseViewModel
import com.fangga.features.onboard.presentation.event.OnboardEvent
import com.fangga.features.onboard.presentation.state.OnboardState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardViewModel @Inject constructor(
    private val userDataStore: UserDataStore,
    private val navigator: NavigationService
) : BaseViewModel<OnboardState, OnboardEvent>(OnboardState()) {


    override suspend fun handleEvent(event: OnboardEvent) {
        when (event) {
            is OnboardEvent.SkipOnboard -> handleSkipOnboard()
            is OnboardEvent.OnNextClicked -> handleNextClicked()
            is OnboardEvent.OnStartClicked -> {
                viewModelScope.launch {
                    userDataStore.setPassedOnboardStatus(true)
                    navigateToHome()
                }
            }
            is OnboardEvent.OnPageChanged -> handlePageChanged(event.page)
        }
    }

    private fun handleNextClicked() {
        viewModelScope.launch {
            val nextPage = uiState.value.currentOnboardPage + 1
            val isLastPage = nextPage == 1
            updateUiState { copy(currentOnboardPage = if (nextPage <= 1) nextPage else 1, isLastPage = isLastPage) }
        }
    }


    private fun handleSkipOnboard() {
        viewModelScope.launch {
            updateUiState { copy(currentOnboardPage = 1, isLastPage = true) }
        }
    }

    private fun handlePageChanged(page: Int) {
        viewModelScope.launch {
            val isLastPage = page == 1
            updateUiState {
                copy(currentOnboardPage = page, isLastPage = isLastPage)
            }
        }
    }

    private fun navigateToHome() {
        navigator.navigateTo("home")
    }
}