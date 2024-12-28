package com.fangga.tips.presentation

import com.fangga.core.navigation.NavigationService
import com.fangga.core.presentation.BaseViewModel
import com.fangga.tips.data.Static
import com.fangga.tips.presentation.event.TipsDetailEvent
import com.fangga.tips.presentation.state.TipsDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * **Class:** TipsDetailViewModel
 *
 * **Purpose:**
 * A ViewModel class that manages the state and logic for the tips detail
 * screen. It loads tips data based on the provided ID and handles navigation
 * back to the previous screen.
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
 * - Extends `BaseViewModel<TipsDetailState, TipsDetailEvent>`, which provides
 *   a base implementation for managing UI state and events.
 *
 * **Functionality:**
 * - Loads tips data based on the provided ID.
 * - Navigates back to the previous screen using the `NavigationService`.
 * - Manages the UI state using the `TipsDetailState` data class.
 * - Handles events using the `TipsDetailEvent` sealed class.
 *
 * **Methods:**
 * - `handleEvent(event: TipsDetailEvent)`: Handles the different events
 *   triggered by the UI.
 *
 * **Usage:**
 * Use this class as the ViewModel for the tips detail screen in your
 * application.
 */

@HiltViewModel
class TipsDetailViewModel @Inject constructor(
    private val navigator: NavigationService
) : BaseViewModel<TipsDetailState, TipsDetailEvent>(TipsDetailState()) {

    override suspend fun handleEvent(event: TipsDetailEvent) {
        when (event) {
            is TipsDetailEvent.LoadTipsData -> updateUiState { copy(tipsData = Static.tipsDetail.find { it.tipsId == (event.tipsId) }) }
            TipsDetailEvent.OnBackClick -> navigator.goBack()
        }
    }
}