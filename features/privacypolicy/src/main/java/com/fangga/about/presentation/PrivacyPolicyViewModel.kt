package com.fangga.about.presentation

import com.fangga.about.presentation.event.PrivacyPolicyEvent
import com.fangga.about.presentation.state.PrivacyPolicyState
import com.fangga.core.navigation.NavigationService
import com.fangga.core.presentation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * **Class:** PrivacyPolicyViewModel
 *
 * **Purpose:**
 * A ViewModel class that manages the state and logic for the privacy policy
 * screen. It handles user interactions and navigates back to the previous
 * screen.
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
 * - Extends `BaseViewModel<PrivacyPolicyState, PrivacyPolicyEvent>`, which
 *   provides a base implementation for managing UI state and events.
 *
 * **Functionality:**
 * - Handles user interactions such as clicking the back button.
 * - Navigates back to the previous screen using the `NavigationService`.
 * - Manages the UI state using the `PrivacyPolicyState` data class.
 * - Handles events using the `PrivacyPolicyEvent` sealed class.
 *
 * **Methods:**
 * - `handleEvent(event: PrivacyPolicyEvent)`: Handles the different events
 *   triggered by the UI.
 *
 * **Usage:**
 * Use this class as the ViewModel for the privacy policy screen in your
 * application.
 */


@HiltViewModel
class PrivacyPolicyViewModel @Inject constructor(
    private val navigator: NavigationService
) : BaseViewModel<PrivacyPolicyState, PrivacyPolicyEvent>(PrivacyPolicyState()) {

    override suspend fun handleEvent(event: PrivacyPolicyEvent) {
        when (event) {
            is PrivacyPolicyEvent.OnBackClick -> navigator.goBack()
        }
    }
}