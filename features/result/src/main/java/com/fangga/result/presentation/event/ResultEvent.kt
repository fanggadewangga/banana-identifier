package com.fangga.result.presentation.event

import com.fangga.core.model.result.Result

sealed class ResultEvent {
    data class ShowModal(val isShowModal: Boolean) : ResultEvent()
    data object RepeatScan: ResultEvent()
    data class SaveResult(val result: Result): ResultEvent()
    data class DeleteSavedResult(val resultId: String): ResultEvent()
    data object NavigateBack: ResultEvent()
}