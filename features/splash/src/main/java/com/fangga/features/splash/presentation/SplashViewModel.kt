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