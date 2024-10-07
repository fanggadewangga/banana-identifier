package com.fangga.core.components.common

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.fangga.core.resource.greenPrimary
import com.fangga.core.resource.whiteBanana

@Composable
fun AppLinearProgressIndicator(
    modifier: Modifier = Modifier,
    progress: Float,
    height: Dp = 24.dp,
    progressColor: Color = greenPrimary,
    backgroundColor: Color = Color.White,
    borderColor: Color = greenPrimary,
    shape: Shape = RoundedCornerShape(16.dp)
) {
    Box(
        modifier = modifier
            .clip(shape)
            .background(backgroundColor, shape)
            .border(width = 1.dp, color = borderColor, shape = shape)
            .height(height)
    ) {
        Box(
            contentAlignment = Alignment.CenterEnd,
            modifier = Modifier
                .background(progressColor, shape)
                .fillMaxHeight()
                .fillMaxWidth(progress)
        ) {
            AppImage(
                imageUrl = whiteBanana,
                contentDescription = "White banana",
                modifier = Modifier
                    .padding(end = 10.dp)
                    .size(18.dp)
                    .align(Alignment.CenterEnd)
            )
        }
    }
}