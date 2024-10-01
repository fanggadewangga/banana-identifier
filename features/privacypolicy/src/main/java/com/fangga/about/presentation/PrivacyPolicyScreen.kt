package com.fangga.about.presentation

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
import com.fangga.about.presentation.components.PrivacyAndPolicyContent
import com.fangga.about.presentation.event.PrivacyPolicyEvent
import com.fangga.core.components.common.AppTopBar

@Composable
fun PrivacyPolicyScreen(
    screenWidth: Int,
    screenHeight: Int,
) {

    val viewModel = hiltViewModel<PrivacyPolicyViewModel>()

    Scaffold(
        topBar = {
            AppTopBar(
                title = "Kebijakan Privasi",
                onNavigateBack = { viewModel.onEvent(PrivacyPolicyEvent.OnBackClick) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height((screenHeight * 0.1).dp)
            )
        }
    ) {
        val topPadding = it.calculateTopPadding()

        Surface(color = Color.White, modifier = Modifier.fillMaxSize()) {
            PrivacyAndPolicyContent(
                modifier = Modifier.padding(
                    top = topPadding + 32.dp,
                    bottom = 32.dp
                )
            )
        }
    }
}