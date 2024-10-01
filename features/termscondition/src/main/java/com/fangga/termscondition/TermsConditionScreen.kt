package com.fangga.termscondition

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.fangga.core.components.common.AppTopBar
import com.fangga.termscondition.components.TermsConditionContent
import com.fangga.termscondition.event.TermsConditionEvent

@Composable
fun TermsConditionScreen(
    screenWidth: Int,
    screenHeight: Int,
) {

    val viewModel = hiltViewModel<TermsConditionViewModel>()

    Scaffold(
        topBar = {
            AppTopBar(
                title = "Syarat dan Ketentuan",
                onNavigateBack = { viewModel.onEvent(TermsConditionEvent.OnBackClick) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height((screenHeight * 0.1).dp)
            )
        }
    ) {
        val topPadding = it.calculateTopPadding()

        Surface(color = Color.White, modifier = Modifier.fillMaxSize()) {
            TermsConditionContent(
                modifier = Modifier.padding(
                    top = topPadding + 32.dp,
                    bottom = 32.dp
                )
            )
        }
    }
}