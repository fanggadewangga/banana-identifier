package com.fangga.features.splash.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.fangga.features.splash.presentation.components.SplashContent
import com.fangga.features.splash.presentation.event.SplashUiEvent

@Composable
fun SplashScreen(screenWidth: Int) {

    val viewModel = hiltViewModel<SplashViewModel>()

    LaunchedEffect(Unit) {
        viewModel.onEvent(SplashUiEvent.CheckPassedOnboardStatus)
    }

    SplashContent(screenWidth = screenWidth)
}