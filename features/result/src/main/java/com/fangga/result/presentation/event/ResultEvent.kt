package com.fangga.result.presentation.event

import com.fangga.core.model.result.ScanResult

sealed class ResultEvent {
    data class ShowModal(val isShowModal: Boolean) : ResultEvent()
    data object RepeatScan: ResultEvent()
    data class SaveResult(val scanResult: ScanResult) : ResultEvent()
    data class DeleteSavedResult(val resultId: String): ResultEvent()
    data object NavigateBack: ResultEvent()
    data class ShowDeletionConfirmation(val isShowDeletionConfirmation: Boolean): ResultEvent()
}