package com.fangga.core.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * **Abstract Class:** BaseViewModel
 *
 * **Purpose:**
 * An abstract base class for ViewModels that provides a common implementation
 * for managing UI state and events. This class simplifies the process of
 * creating ViewModels by providing a standardized way to handle state updates
 * and event processing.
 *
 * **Type Parameters:**
 * - `UiState`: The type of the UI state data class.
 * - `Event`: The type of the event sealed class.
 *
 * **Parameters:**
 * - `initialState`: The initial state of the UI.
 *
 * **Properties:**
 * - `events`: A `MutableSharedFlow` for emitting events.
 * - `_uiState`: A `MutableStateFlow` for managing the UI state.
 * - `uiState`: A `StateFlow` for observing the UI state.
 *
 * **Functionality:**
 * - Provides a `MutableSharedFlow` for emitting events.
 * - Provides a `MutableStateFlow` for managing the UI state.
 * - Provides a `StateFlow` for observing the UI state.
 * - Provides an `init` block that collects events and calls the `handleEvent`
 *   method.
 * - Provides an abstract `handleEvent` method that must be implemented by
 *   subclasses.
 * - Provides an `updateUiState` method for updating the UI state.
 * - Provides an `onEvent` method for emitting events.
 *
 * **Methods:**
 * - `handleEvent(event: Event)`: An abstract method that must be implemented
 *   by subclasses to handle events.
 * - `updateUiState(update: UiState.() -> UiState)`: Updates the UI state using
 *   a lambda function.
 * - `onEvent(event: Event)`: Emits an event to the `events` flow.
 *
 * **Usage:**
 * Use this class as the base class for your ViewModels to simplify state
 * management and event handling.
 *
 */

abstract class BaseViewModel<UiState, Event>(initialState: UiState) : ViewModel() {

    private val events = MutableSharedFlow<Event>(replay = 0)
    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(initialState)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            events.collect { event ->
                handleEvent(event)
            }
        }
    }

    protected abstract suspend fun handleEvent(event: Event)

    protected fun updateUiState(update: UiState.() -> UiState) {
        _uiState.update { _uiState.value.update() }
    }

    fun onEvent(event: Event) {
        viewModelScope.launch {
            events.emit(event)
        }
    }
}