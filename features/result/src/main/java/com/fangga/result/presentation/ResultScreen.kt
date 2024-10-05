package com.fangga.result.presentation

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.fangga.core.model.result.Result
import com.fangga.result.data.static.Static
import com.fangga.result.presentation.component.ResultContent
import com.fangga.result.presentation.event.ResultEvent

@Composable
fun ResultScreen(
    isNewResult: Boolean = false,
    screenWidth: Int,
    image: Uri? = null,
    scanResult: Result = Static.resultDetail,
) {

    val viewModel = hiltViewModel<ResultViewModel>()
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    ResultContent(
        result = scanResult,
        screenWidth = screenWidth,
        isNewResult = isNewResult,
        isShowBottomSheet = state.isShowModal,
        onRepeatScan = { viewModel.onEvent(ResultEvent.RepeatScan) },
        onSaveResult = { viewModel.onEvent(ResultEvent.SaveResult(scanResult)) },
        onDeleteSavedResult = { viewModel.onEvent(ResultEvent.DeleteSavedResult(scanResult.resultId)) },
        onBackClick = { viewModel.onEvent(ResultEvent.NavigateBack) },
        onDismiss = { viewModel.onEvent(ResultEvent.ShowModal(false)) },
        modifier = Modifier.fillMaxSize().background(color = Color.Red)
    )
}