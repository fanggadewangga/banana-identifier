package com.fangga.core.components.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.fangga.core.resource.backIcon
import com.fangga.core.resource.h11SemiBold
import com.fangga.core.utils.noRippleClickable

@Composable
fun AppTopBar(
    modifier: Modifier = Modifier,
    title: String,
    onNavigateBack: () -> Unit,
) {
    Box(
        modifier = modifier
            .background(color = Color.White)
    ) {
        AppImage(
            imageUrl = backIcon,
            contentDescription = "Back button",
            modifier = Modifier
                .noRippleClickable { onNavigateBack() }
                .align(Alignment.BottomStart)
                .padding(bottom = 24.dp, start = 16.dp)
                .size(24.dp)
        )
        AppText(
            text = title,
            textStyle = h11SemiBold,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 24.dp)
        )
    }
}