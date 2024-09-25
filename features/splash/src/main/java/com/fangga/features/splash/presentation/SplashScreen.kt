package com.fangga.features.splash.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.fangga.features.splash.presentation.components.SplashContent
import com.fangga.features.splash.presentation.event.SplashUiEvent

@Composable
fun SplashScreen(screenWidth: Int) {

    val viewModel: SplashViewModel = hiltViewModel<SplashViewModel>()
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(true) {
        viewModel.onEvent(SplashUiEvent.CheckPassedOnboardStatus)
    }

    when (state.hasPassedOnboard) {
        true -> viewModel.onEvent(SplashUiEvent.NavigateToHome)
        false -> viewModel.onEvent(SplashUiEvent.NavigateToOnboard)
    }

    SplashContent(screenWidth = screenWidth)
}