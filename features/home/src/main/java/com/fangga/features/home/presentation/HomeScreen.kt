package com.fangga.features.home.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.fangga.core.components.common.AppText
import com.fangga.core.components.common.apptoast.AppToastUtil
import com.fangga.core.components.common.apptoast.Error
import com.fangga.core.components.common.apptoast.Success
import com.fangga.core.data.model.enums.ResultItemSwipeType
import com.fangga.core.resource.h10Bold
import com.fangga.features.home.presentation.components.AboutApp
import com.fangga.features.home.presentation.components.HomeBackground
import com.fangga.features.home.presentation.components.LatestResult
import com.fangga.features.home.presentation.components.ScanShortcut
import com.fangga.features.home.presentation.components.TipsAndRecommendation
import com.fangga.features.home.presentation.event.HomeEvent

@Composable
fun HomeScreen(screenHeight: Int, screenWidth: Int) {

    val viewModel = hiltViewModel<HomeViewModel>()
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.onEvent(HomeEvent.LoadLatestResult)
    }

    if (state.showSuccessToast) {
        AppToastUtil.ShowAppToast(message = state.successMessage.toString(), type = Success())
        viewModel.onEvent(HomeEvent.HideToast)
    }

    if (state.showErrorToast) {
        AppToastUtil.ShowAppToast(message = state.errorMessage.toString(), type = Error())
        viewModel.onEvent(HomeEvent.HideToast)
    }

    Box(modifier = Modifier.fillMaxSize()) {

        HomeBackground(modifier = Modifier.fillMaxWidth())

        LazyColumn(
            contentPadding = PaddingValues(
                vertical = (screenHeight * 0.08).dp,
                horizontal = (screenWidth * 0.06).dp
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            item {
                AppText(text = "Selamat Datang!", textStyle = h10Bold, color = Color.White)
            }

            item {
                if (state.latestResult != null)
                    LatestResult(
                        data = state.latestResult!!,
                        swipeType = ResultItemSwipeType.SAVE,
                        screenHeight = screenHeight,
                        screenWidth = screenWidth,
                        onItemClicked = {
                            viewModel.onEvent(HomeEvent.OnLatestResultClicked(context))
                        },
                        onActionClicked = {
                            viewModel.onEvent(
                                HomeEvent.OnLatestResultSwiped(state.latestResult!!)
                            )
                        },
                        modifier = Modifier.padding(top = 8.dp)
                    )
                else
                    ScanShortcut(
                        onClick = { viewModel.onEvent(HomeEvent.OnScanShortcutClicked) },
                        modifier = Modifier.padding(top = 24.dp)
                    )
            }

            item {
                Spacer(modifier = Modifier.height(24.dp))
                TipsAndRecommendation(
                    tipsData = state.tipsData,
                    screenWidth = screenWidth,
                    screenHeight = screenHeight,
                    onTipsItemClicked = { tipsId ->
                        viewModel.onEvent(
                            HomeEvent.OnTipsItemClicked(
                                tipsId
                            )
                        )
                    }
                )
            }

            item {
                Spacer(modifier = Modifier.height(24.dp))
                AboutApp(
                    onClicked = { viewModel.onEvent(HomeEvent.OnAboutAppClicked(it)) }
                )
            }
        }
    }
}