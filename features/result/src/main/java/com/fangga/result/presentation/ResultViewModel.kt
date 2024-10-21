package com.fangga.result.presentation

import android.content.Context
import androidx.lifecycle.viewModelScope
import com.fangga.core.data.base.Resource
import com.fangga.core.data.datasource.LocalDataSource
import com.fangga.core.data.model.result.ScanResult
import com.fangga.core.navigation.NavigationService
import com.fangga.core.presentation.BaseViewModel
import com.fangga.core.utils.Constants.LATEST_RESULT_ID
import com.fangga.core.utils.toScanResultEntity
import com.fangga.result.presentation.event.ResultEvent
import com.fangga.result.presentation.state.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class ResultViewModel @Inject constructor(
    private val navigator: NavigationService,
    private val localDataSource: LocalDataSource
) : BaseViewModel<ResultState, ResultEvent>(ResultState()) {

    private fun deleteSavedResult(resultId: String) {
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

                    is Resource.Error -> updateUiState {
                        copy(
                            isLoading = false,
                            errorMessage = result.message,
                            showErrorToast = true
                        )
                    }

                    is Resource.Loading -> updateUiState { copy(isLoading = true) }
                    is Resource.Success -> {
                        updateUiState {
                            copy(
                                isLoading = false,
                                successMessage = "Berhasil menghapus hasil scan",
                                showSuccessToast = true
                            )
                        }
                        viewModelScope.launch {
                            delay(1000)
                            navigateBack()
                        }
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
                        viewModelScope.launch {
                            delay(1000)
                            navigateBack()
                        }
                    }
                }
            }
        }
    }

    private fun navigateToSavedScreen() {
        navigator.navigateTo("saved_result") {
            popUpTo("saved_result") { inclusive = true }
        }
    }

    private fun navigateToScanScreen() {
        navigator.navigateTo("scan_camera") {
            popUpTo("scan_camera") { inclusive = true }
        }
    }

    private fun saveResult(context: Context, item: ScanResult) {
        val entity = item.toScanResultEntity(context)
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
                        deleteLatestResult()
                        updateUiState {
                            copy(
                                isLoading = false,
                                successMessage = "Berhasil menyimpan data identifikasi pisang",
                                showSuccessToast = true
                            )
                        }
                    }
                }
            }
        }
    }

    private fun showModal(isShowModal: Boolean) {
        updateUiState { copy(isShowModal = isShowModal) }
    }

    private fun navigateBack() {
        navigator.goBack()
    }

    private fun showDeletionConfirmation(isShowDeletionConfirmation: Boolean) {
        updateUiState { copy(isShowDeletionConfirmation = isShowDeletionConfirmation) }
    }

    private fun hideToast() {
        updateUiState {
            copy(
                showSuccessToast = false,
                showErrorToast = false
            )
        }
    }

    override suspend fun handleEvent(event: ResultEvent) {
        when (event) {
            is ResultEvent.DeleteSavedResult -> deleteSavedResult(event.resultId)
            ResultEvent.RepeatScan -> navigateToScanScreen()
            is ResultEvent.SaveResult -> saveResult(event.context, event.scanResult)
            is ResultEvent.ShowModal -> showModal(event.isShowModal)
            ResultEvent.NavigateBack -> navigateBack()
            is ResultEvent.ShowDeletionConfirmation -> showDeletionConfirmation(event.isShowDeletionConfirmation)
            ResultEvent.HideToast -> hideToast()
        }
    }
}