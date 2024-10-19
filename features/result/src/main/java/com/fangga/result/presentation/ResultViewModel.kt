package com.fangga.result.presentation

import androidx.lifecycle.viewModelScope
import com.fangga.core.data.base.Resource
import com.fangga.core.data.datasource.LocalDataSource
import com.fangga.core.data.source.room.entity.ScanResultEntity
import com.fangga.core.navigation.NavigationService
import com.fangga.core.presentation.BaseViewModel
import com.fangga.result.presentation.event.ResultEvent
import com.fangga.result.presentation.state.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
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
                    is Resource.Empty -> updateUiState { copy(isLoading = false) }
                    is Resource.Error -> updateUiState { copy(isLoading = false) }
                    is Resource.Loading -> updateUiState { copy(isLoading = true) }
                    is Resource.Success -> updateUiState {
                        copy(
                            isLoading = false,
                            message = "Berhasil menghapus hasil scan"
                        )
                    }
                }
            }
        }
    }

    private fun navigateToScanScreen() {
        navigator.navigateTo("scan_camera") {
            popUpTo("scan_camera") { inclusive = true }
        }
    }

    private fun saveResult(scanResult: ScanResultEntity) {
        viewModelScope.launch {
            localDataSource.insertNewScanResult(scanResult).collectLatest { result ->
                when (result) {
                    is Resource.Empty -> {}
                    is Resource.Error -> updateUiState {
                        copy(
                            isLoading = false,
                            isError = true,
                            message = result.message
                        )
                    }

                    is Resource.Loading -> updateUiState { copy(isLoading = true) }
                    is Resource.Success -> updateUiState {
                        copy(
                            isLoading = false,
                            message = "Berhasil menyimpan hasil scan"
                        )
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

    override suspend fun handleEvent(event: ResultEvent) {
        when (event) {
            is ResultEvent.DeleteSavedResult -> deleteSavedResult(event.resultId)
            ResultEvent.RepeatScan -> navigateToScanScreen()
            is ResultEvent.SaveResult -> saveResult(event.scanResult)
            is ResultEvent.ShowModal -> showModal(event.isShowModal)
            ResultEvent.NavigateBack -> navigateBack()
            is ResultEvent.ShowDeletionConfirmation -> showDeletionConfirmation(event.isShowDeletionConfirmation)
        }
    }
}