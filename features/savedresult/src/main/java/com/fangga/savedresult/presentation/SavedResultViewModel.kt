package com.fangga.savedresult.presentation

import android.content.Context
import android.net.Uri
import androidx.lifecycle.viewModelScope
import com.fangga.core.data.base.Resource
import com.fangga.core.data.datasource.LocalDataSource
import com.fangga.core.data.model.result.ScanResultList
import com.fangga.core.navigation.NavigationService
import com.fangga.core.presentation.BaseViewModel
import com.fangga.core.utils.saveBitmapToFileAndGetUri
import com.fangga.savedresult.presentation.event.SavedResultEvent
import com.fangga.savedresult.presentation.state.SavedResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * **Class:** SavedResultViewModel
 *
 * **Purpose:**
 * A ViewModel class that manages the state and logic for the saved result
 * screen. It loads saved scan results, handles user interactions, and
 * navigates between screens.
 *
 * **Annotations:**
 * - `@HiltViewModel`: Indicates that this class is a Hilt ViewModel, which
 *   enables dependency injection.
 *
 * **Parameters:**
 * - `localDataSource`: An instance of `LocalDataSource` for accessing local data.
 * - `navigator`: An instance of `NavigationService` for navigating between
 *   screens.
 *
 * **Inheritance:**
 * - Extends `BaseViewModel<SavedResultState, SavedResultEvent>`, which provides
 *   a base implementation for managing UI state and events.
 *
 * **Functionality:**
 * - Loads all saved scan results from the local data source.
 * - Handles user interactions such as clicking the back button, swiping to
 *   delete a result, and clicking a result item.
 * - Navigates to the home screen and the scan result detail screen using the
 *   `NavigationService`.
 * - Manages the UI state using the `SavedResultState` data class.
 * - Handles events using the `SavedResultEvent` sealed class.
 *
 * **Methods:**
 * - `loadSavedResult()`: Loads all saved scan results from the local data
 *   source and updates the UI state.
 * - `onBackClicked()`: Navigates to the home screen.
 * - `deleteResult(resultId: String)`: Deletes a scan result from the local
 *   data source and updates the UI state.
 * - `navigateToDetail(context: Context, item: ScanResultList)`: Navigates to
 *   the scan result detail screen with the selected result data.
 * - `hideToast()`: Hides the error and success toasts.
 * - `handleEvent(event: SavedResultEvent)`: Handles the different events
 *   triggered by the UI.
 *
 * **Usage:**
 * Use this class as the ViewModel for the saved result screen in your
 * application.
 */


@HiltViewModel
class SavedResultViewModel @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val navigator: NavigationService
) : BaseViewModel<SavedResultState, SavedResultEvent>(SavedResultState()) {

    private fun loadSavedResult() {
        viewModelScope.launch {
            localDataSource.getAllScanResults().collectLatest { result ->
                when (result) {
                    is Resource.Empty -> updateUiState {
                        copy(
                            isLoading = false,
                            results = emptyList()
                        )
                    }

                    is Resource.Error -> updateUiState {
                        copy(
                            isLoading = false,
                            errorMessage = result.message.toString(),
                            showErrorToast = true
                        )
                    }

                    is Resource.Loading -> updateUiState { copy(isLoading = true) }
                    is Resource.Success -> updateUiState {
                        copy(
                            isLoading = false,
                            results = result.data!!
                        )
                    }
                }
            }
        }
    }

    private fun onBackClicked() {
        navigator.navigateTo("home") {
            popUpTo("home") {
                inclusive = true
            }
        }
    }

    private fun deleteResult(resultId: String) {
        viewModelScope.launch {
            localDataSource.deleteScanResultById(resultId).collectLatest { result ->
                when (result) {
                    is Resource.Empty -> updateUiState {
                        copy(
                            isLoading = false,
                            errorMessage = "Terjadi kesalahan",
                            showErrorToast = true
                        )
                    }

                    is Resource.Error -> {
                        updateUiState {
                            copy(
                                isLoading = false,
                                errorMessage = result.message.toString(),
                                showErrorToast = true
                            )
                        }
                    }

                    is Resource.Loading -> updateUiState { copy(isLoading = true) }
                    is Resource.Success -> {
                        loadSavedResult()
                        updateUiState {
                            copy(
                                isLoading = false,
                                successMessage = "Berhasil menghapus riwayat scan",
                                showSuccessToast = true
                            )
                        }
                    }
                }
            }
        }
    }

    private fun navigateToDetail(context: Context, item: ScanResultList) {
        val capturedImage = item.image
        val capturedImageFile = saveBitmapToFileAndGetUri(context, capturedImage)
        val encodedImageUri = Uri.encode(capturedImageFile)

        navigator.navigateTo("scan_result/false/${item.resultId}/${encodedImageUri}/${item.bananaType}/${item.ripenessType}") {
            launchSingleTop = true
            restoreState = true
        }
    }

    private fun hideToast() {
        updateUiState {
            copy(
                showErrorToast = false,
                showSuccessToast = false
            )
        }
    }

    override suspend fun handleEvent(event: SavedResultEvent) {
        when (event) {
            SavedResultEvent.LoadSavedResult -> loadSavedResult()
            SavedResultEvent.OnBackClicked -> onBackClicked()
            is SavedResultEvent.OnSwipeToDelete -> deleteResult(event.resultId)
            is SavedResultEvent.NavigateToDetail -> navigateToDetail(event.context, event.item)
            SavedResultEvent.HideToast -> hideToast()
        }
    }
}