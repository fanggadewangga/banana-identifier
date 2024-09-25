package com.fangga.features.onboard.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.fangga.core.components.common.AppButton
import com.fangga.core.components.common.AppText
import com.fangga.core.resource.bodyText14Regular
import com.fangga.core.resource.greenPrimary
import com.fangga.features.onboard.domain.OnboardItem
import com.fangga.features.onboard.presentation.components.OnboardIndicator
import com.fangga.features.onboard.presentation.components.OnboardItem
import kotlinx.coroutines.launch

@Composable
fun OnboardScreen(screenHeight: Int) {

    val viewModel = hiltViewModel<OnboardViewModel>()
    val pages = listOf(
        OnboardItem.FirstOnboard,
        OnboardItem.SecondOnboard,
    )
    val state by viewModel.uiState.collectAsState()
    val pagerState = rememberPagerState(
        initialPage = state.currentOnboardPage,
        pageCount = { pages.size }
    )
    val coroutineScope = rememberCoroutineScope()

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
        ) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.height((screenHeight * 0.78).dp)
            ) {
                OnboardItem(item = pages[it], screenHeight = screenHeight)
            }
            OnboardIndicator(pagerState = pagerState)
        }
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = (screenHeight * 0.06).dp)
        ) {
            if (pagerState.currentPage != pages.size - 1)
                AppText(
                    text = "Lewati",
                    textStyle = bodyText14Regular,
                    color = greenPrimary,
                    modifier = Modifier.clickable {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage - 1)
                        }
                    })
            AppButton(
                onClick = { coroutineScope.launch { pagerState.animateScrollToPage(pagerState.currentPage + 1) } },
                contentPadding = PaddingValues(horizontal = 36.dp, vertical = 12.dp),
                modifier = Modifier.then(
                    if (pagerState.currentPage == pages.size - 1) Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp) else Modifier
                )
            ) {
                AppText(
                    text = if (pagerState.currentPage == pages.size - 1) "Mulai" else "Selanjutnya",
                    textStyle = bodyText14Regular,
                    color = Color.White
                )
            }
        }
    }
}