package com.fangga.savedresult.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.fangga.core.components.common.AppTopBar
import com.fangga.core.components.common.apptoast.AppToastUtil
import com.fangga.core.components.common.apptoast.Error
import com.fangga.core.components.common.apptoast.Success
import com.fangga.savedresult.presentation.component.SavedResultContent
import com.fangga.savedresult.presentation.event.SavedResultEvent

@Composable
fun SavedResultScreen(screenHeight: Int) {

    val viewModel = hiltViewModel<SavedResultViewModel>()
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        viewModel.onEvent(SavedResultEvent.LoadSavedResult)
    }
    if (state.showSuccessToast) {
        AppToastUtil.ShowAppToast(message = state.successMessage.toString(), type = Success())
        viewModel.onEvent(SavedResultEvent.HideToast)
    }

    if (state.showErrorToast) {
        AppToastUtil.ShowAppToast(message = state.errorMessage.toString(), type = Error())
        viewModel.onEvent(SavedResultEvent.HideToast)
    }

    Scaffold(
        topBar = {
            AppTopBar(
                title = "Tersimpan",
                onNavigateBack = { viewModel.onEvent(SavedResultEvent.OnBackClicked) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height((screenHeight * 0.1).dp)
            )
        }
    ) {

        val topPadding = it.calculateTopPadding()

        SavedResultContent(
            results = state.results,
            topPadding = topPadding,
            onSwipeToDelete = { id -> viewModel.onEvent(SavedResultEvent.OnSwipeToDelete(id)) },
            onNavigateToDetail = { item ->
                viewModel.onEvent(
                    SavedResultEvent.NavigateToDetail(
                        context,
                        item
                    )
                )
            },
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
        )
    }
}