package com.fangga.features.onboard.presentation

import androidx.lifecycle.viewModelScope
import com.fangga.core.datasource.datastore.UserDataStore
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
            is OnboardEvent.SkipOnboard -> {}
            is OnboardEvent.OnNextClicked -> {}
            is OnboardEvent.OnPreviousClicked -> {}
            is OnboardEvent.OnStartClicked -> {
                viewModelScope.launch {
                    userDataStore.setPassedOnboardStatus(true)
                    navigateToHome()
                }
            }
        }
    }

    private fun navigateToHome() {
        navigator.navigateTo("home")
    }
}