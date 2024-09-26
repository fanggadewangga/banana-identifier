package com.fangga.features.onboard.presentation.state

data class OnboardState(
    val currentOnboardPage: Int = 0,
    val isLastPage: Boolean = false
)
