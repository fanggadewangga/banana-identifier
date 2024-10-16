package com.fangga.result.presentation

import android.util.Log
import com.fangga.core.data.datasource.LocalDataSource
import com.fangga.core.data.model.result.ScanResult
import com.fangga.core.navigation.NavigationService
import com.fangga.core.presentation.BaseViewModel
import com.fangga.result.presentation.event.ResultEvent
import com.fangga.result.presentation.state.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ResultViewModel @Inject constructor(
    private val navigator: NavigationService,
    private val localDataSource: LocalDataSource
) : BaseViewModel<ResultState, ResultEvent>(ResultState()) {

    private fun deleteSavedResult(resultId: String) {
        Log.d("ResultViewModel", "deleteSavedResult: $resultId")
    }

    private fun navigateToScanScreen() {
        navigator.navigateTo("scan_camera") {
            popUpTo("scan_camera") { inclusive = true }
        }
    }

    private fun saveResult(scanResult: ScanResult) {
        Log.d("ResultViewModel", "saveResult: $scanResult")
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