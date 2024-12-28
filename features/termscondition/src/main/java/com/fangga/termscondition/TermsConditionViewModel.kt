package com.fangga.termscondition

import com.fangga.core.navigation.NavigationService
import com.fangga.core.presentation.BaseViewModel
import com.fangga.termscondition.event.TermsConditionEvent
import com.fangga.termscondition.state.TermsConditionState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * **Class:** TermsConditionViewModel
 *
 * **Purpose:**
 * A ViewModel class that manages the state and logic for the terms and
 * conditions screen. It handles user interactions and navigates back to the
 * previous screen.
 *
 * **Annotations:**
 * - `@HiltViewModel`: Indicates that this class is a Hilt ViewModel, which
 *   enables dependency injection.
 *
 * **Parameters:**
 * - `navigator`: An instance of `NavigationService` for navigating between
 *   screens.
 *
 * **Inheritance:**
 * - Extends `BaseViewModel<TermsConditionState, TermsConditionEvent>`, which
 *   provides a base implementation for managing UI state and events.
 *
 * **Functionality:**
 * - Handles user interactions such as clicking the back button.
 * - Navigates back to the previous screen using the `NavigationService`.
 * - Manages the UI state using the `TermsConditionState` data class.
 * - Handles events using the `TermsConditionEvent` sealed class.
 *
 * **Methods:**
 * - `handleEvent(event: TermsConditionEvent)`: Handles the different events
 *   triggered by the UI.
 *
 * **Usage:**
 * Use this class as the ViewModel for the terms and conditions screen in your
 * application.
 */

@HiltViewModel
class TermsConditionViewModel @Inject constructor(
    private val navigator: NavigationService
) : BaseViewModel<TermsConditionState, TermsConditionEvent>(TermsConditionState()) {
    override suspend fun handleEvent(event: TermsConditionEvent) {
        when (event) {
            is TermsConditionEvent.OnBackClick -> navigator.goBack()
        }
    }
}