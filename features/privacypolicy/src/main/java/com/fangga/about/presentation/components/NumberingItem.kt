package com.fangga.about.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import com.fangga.core.components.common.AppText
import com.fangga.core.resource.greenPrimary
import com.fangga.core.resource.h12SemiBold

@Composable
fun NumberingItem(
    modifier: Modifier = Modifier,
    number: String,
    shape: Shape = CircleShape,
    textColor: Color = Color.White,
    backgroundColor: Color = greenPrimary,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.background(color = backgroundColor, shape = shape)
    ) {
        AppText(text = number, textStyle = h12SemiBold, color = textColor)
    }
}