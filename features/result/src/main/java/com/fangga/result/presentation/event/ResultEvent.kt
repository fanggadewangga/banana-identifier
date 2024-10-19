package com.fangga.result.presentation.event

import com.fangga.core.data.source.room.entity.ScanResultEntity

sealed class ResultEvent {
    data class ShowModal(val isShowModal: Boolean) : ResultEvent()
    data object RepeatScan: ResultEvent()
    data class SaveResult(val scanResult: ScanResultEntity) : ResultEvent()
    data class DeleteSavedResult(val resultId: String): ResultEvent()
    data object NavigateBack: ResultEvent()
    data class ShowDeletionConfirmation(val isShowDeletionConfirmation: Boolean): ResultEvent()
}