package com.fangga.result.presentation

import com.fangga.core.model.result.Result
import com.fangga.core.navigation.NavigationService
import com.fangga.core.presentation.BaseViewModel
import com.fangga.result.presentation.event.ResultEvent
import com.fangga.result.presentation.state.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ResultViewModel @Inject constructor(
    private val navigator: NavigationService
) : BaseViewModel<ResultState, ResultEvent>(ResultState()) {

    private fun deleteSavedResult(resultId: String) {

    }

    private fun navigateToScanScreen() {
        navigator.navigateTo("scan_camera")
    }

    private fun saveResult(result: Result) {

    }

    private fun showModal(isShowModal: Boolean) {
        updateUiState { copy(isShowModal = isShowModal) }
    }

    private fun navigateBack() {
        navigator.goBack()
    }

    override suspend fun handleEvent(event: ResultEvent) {
        when (event) {
            is ResultEvent.DeleteSavedResult -> deleteSavedResult(event.resultId)
            ResultEvent.RepeatScan -> navigateToScanScreen()
            is ResultEvent.SaveResult -> saveResult(event.result)
            is ResultEvent.ShowModal -> showModal(event.isShowModal)
            ResultEvent.NavigateBack -> navigateBack()
        }
    }
}