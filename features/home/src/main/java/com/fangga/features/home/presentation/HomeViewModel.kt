package com.fangga.features.home.presentation

import android.content.Context
import android.net.Uri
import androidx.lifecycle.viewModelScope
import com.fangga.core.data.base.Resource
import com.fangga.core.data.datasource.LocalDataSource
import com.fangga.core.data.model.result.ScanResultList
import com.fangga.core.navigation.NavigationService
import com.fangga.core.presentation.BaseViewModel
import com.fangga.core.utils.Constants.LATEST_RESULT_ID
import com.fangga.core.utils.saveBitmapToFileAndGetUri
import com.fangga.core.utils.toScanResultEntity
import com.fangga.features.home.presentation.event.HomeEvent
import com.fangga.features.home.presentation.state.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

/**
 * **Class:** HomeViewModel
 *
 * **Purpose:**
 * A ViewModel class that manages the state and logic for the home screen. It
 * retrieves the latest scan result, handles user interactions, and navigates
 * to other screens.
 *
 * **Annotations:**
 * - `@HiltViewModel`: Indicates that this class is a Hilt ViewModel, which
 *   enables dependency injection.
 *
 * **Parameters:**
 * - `localDataSource`: An instance of `LocalDataSource` for accessing local data.
 * - `navigationService`: An instance of `NavigationService` for navigating
 *   between screens.
 *
 * **Inheritance:**
 * - Extends `BaseViewModel<HomeState, HomeEvent>`, which provides a base
 *   implementation for managing UI state and events.
 *
 * **Functionality:**
 * - Loads the latest scan result from the local data source.
 * - Handles user interactions such as clicking the scan shortcut, clicking the
 *   latest result, swiping the latest result, clicking a tips item, and
 *   clicking an about app item.
 * - Navigates to other screens using the `NavigationService`.
 * - Manages the UI state using the `HomeState` data class.
 * - Handles events using the `HomeEvent` sealed class.
 *
 * **Methods:**
 * - `loadLatestResult()`: Loads the latest scan result from the local data
 *   source and updates the UI state.
 * - `deleteLatestResult()`: Deletes the latest scan result from the local data
 *   source and updates the UI state.
 * - `hideToast()`: Hides the error and success toasts.
 * - `handleScanShortcutClick()`: Navigates to the scan camera screen.
 * - `handleLatestResultClick(context: Context)`: Navigates to the scan result
 *   screen with the latest result data.
 * - `handleLatestResultSwiped(latestResult: ScanResultList)`: Saves the latest
 *   result to the database and deletes the old one.
 * - `handleTipsItemClicked(tipsId: String)`: Navigates to the tips screen.
 * - `handleAboutAppClicked(aboutId: String)`: Navigates to the privacy policy,
 *   terms and conditions, or rating screen.
 * - `handleEvent(event: HomeEvent)`: Handles the different events triggered by
 *   the UI.
 *
 * **Usage:**
 * Use this class as the ViewModel for the home screen in your application.
 */

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val navigationService: NavigationService
) : BaseViewModel<HomeState, HomeEvent>(HomeState()) {

    private fun loadLatestResult() {
        viewModelScope.launch {
            localDataSource.getLatestScanResult().collect { result ->
                when (result) {
                    is Resource.Empty -> updateUiState { copy(isLoading = false) }
                    is Resource.Error -> updateUiState {
                        copy(
                            isLoading = false,
                            errorMessage = result.message
                        )
                    }

                    is Resource.Loading -> updateUiState { copy(isLoading = true) }
                    is Resource.Success -> updateUiState {
                        copy(
                            isLoading = false,
                            latestResult = result.data
                        )
                    }
                }
            }
        }
    }

    private fun deleteLatestResult() {
        viewModelScope.launch {
            localDataSource.deleteScanResultById(LATEST_RESULT_ID).collect { result ->
                when (result) {
                    is Resource.Empty -> updateUiState { copy(isLoading = false) }
                    is Resource.Error -> updateUiState {
                        copy(
                            isLoading = false,
                            errorMessage = result.message
                        )
                    }

                    is Resource.Loading -> updateUiState { copy(isLoading = true) }
                    is Resource.Success -> {
                        loadLatestResult()
                    }
                }
            }
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

    private fun handleScanShortcutClick() {
        navigationService.navigateTo("scan_camera")
    }

    private fun handleLatestResultClick(context: Context) {
        val latestResult = uiState.value.latestResult

        if (latestResult != null) {
            val capturedImage = latestResult.image
            val capturedImageFile = saveBitmapToFileAndGetUri(context, capturedImage)
            val encodedImageUri = Uri.encode(capturedImageFile)

            navigationService.navigateTo("scan_result/true/${latestResult.resultId}/${encodedImageUri}/${latestResult.bananaType}/${latestResult.ripenessType}") {
                launchSingleTop = true
                restoreState = true
            }
        }

    }

    private fun handleLatestResultSwiped(latestResult: ScanResultList) {
        val entity = latestResult.toScanResultEntity()
        entity.resultId = UUID.randomUUID().toString()

        viewModelScope.launch {
            localDataSource.insertNewScanResult(entity).collectLatest { result ->
                when (result) {
                    is Resource.Empty -> {
                        updateUiState {
                            copy(
                                isLoading = false,
                                showErrorToast = true,
                                errorMessage = "Terjadi kesalahan"
                            )
                        }
                    }

                    is Resource.Error -> {
                        updateUiState {
                            copy(
                                isLoading = false,
                                showErrorToast = true,
                                errorMessage = result.message,
                            )
                        }
                    }

                    is Resource.Loading -> updateUiState { copy(isLoading = true) }
                    is Resource.Success -> {
                        updateUiState {
                            copy(
                                successMessage = "Berhasil menyimpan data identifikasi pisang",
                                showSuccessToast = true
                            )
                        }
                        deleteLatestResult()
                    }
                }
            }
        }
    }

    private fun handleTipsItemClicked(tipsId: String) {
        navigationService.navigateTo("tips/${tipsId}") {
            launchSingleTop = true
            restoreState = true
        }
    }

    private fun handleAboutAppClicked(aboutId: String) {
        when (aboutId) {
            // TODO : Handle Rating Navigation
            "privacy_policy" -> navigationService.navigateTo("privacy_and_policy")
            "terms_and_conditions" -> navigationService.navigateTo("terms_and_condition")
            "rating" -> {}
        }
    }

    override suspend fun handleEvent(event: HomeEvent) {
        when (event) {
            HomeEvent.LoadLatestResult -> loadLatestResult()
            HomeEvent.OnScanShortcutClicked -> handleScanShortcutClick()
            is HomeEvent.OnLatestResultClicked -> handleLatestResultClick(event.context)
            is HomeEvent.OnLatestResultSwiped -> handleLatestResultSwiped(event.latestResult)
            is HomeEvent.OnTipsItemClicked -> handleTipsItemClicked(event.tipsId)
            is HomeEvent.OnAboutAppClicked -> handleAboutAppClicked(event.aboutId)
            HomeEvent.HideToast -> hideToast()
        }
    }
}