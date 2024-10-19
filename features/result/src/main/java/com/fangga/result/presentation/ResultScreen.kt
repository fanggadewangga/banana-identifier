package com.fangga.result.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.fangga.core.data.model.result.ScanResult
import com.fangga.result.presentation.component.ResultContent
import com.fangga.result.presentation.event.ResultEvent

@Composable
fun ResultScreen(
    isNewResult: Boolean,
    screenWidth: Int,
    scanResult: ScanResult,
) {

    val viewModel = hiltViewModel<ResultViewModel>()
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    ResultContent(
        scanResult = scanResult,
        screenWidth = screenWidth,
        isNewResult = isNewResult,
        isShowDeletionConfirmation = state.isShowDeletionConfirmation,
        isShowBottomSheet = state.isShowModal,
        onRepeatScan = { viewModel.onEvent(ResultEvent.RepeatScan) },
        onSaveResult = { //viewModel.onEvent(ResultEvent.SaveResult(scanResult))
        },
        onDeleteSavedResult = { viewModel.onEvent(ResultEvent.DeleteSavedResult(scanResult.resultId)) },
        onBackClick = { viewModel.onEvent(ResultEvent.NavigateBack) },
        onCancelDelete = { viewModel.onEvent(ResultEvent.ShowDeletionConfirmation(false)) },
        onShowDeletionConfirmation = { viewModel.onEvent(ResultEvent.ShowDeletionConfirmation(true)) },
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    )
}