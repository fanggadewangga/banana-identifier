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

/**
 * **Composable Function:** AppLinearProgressIndicator
 *
 * **Purpose:**
 * A custom linear progress indicator composable that provides a visually appealing
 * progress bar with customizable styling.
 *
 * **Parameters:**
 * - `modifier`: Modifier for the layout of the progress indicator.
 * - `progress`: A float value between 0.0 and 1.0 representing the progress.
 * - `height`: The height of the progress indicator.
 * - `progressColor`: The color of the progress bar.
 * - `backgroundColor`: The background color of the progress indicator.
 * - `borderColor`: The border color of the progress indicator.
 * - `shape`: The shape of the progress indicator's corners.
 *
 * **Functionality:**
 * - Creates a `Box` to act as the progress indicator's container.
 * - Uses a `clip` modifier to apply the specified shape to the progress indicator.
 * - Uses a `background` modifier to set the background color of the progress indicator.
 * - Uses a `border` modifier to add a border to the progress indicator.
 * - Uses a `height` modifier to set the height of the progress indicator.
 * - Creates a nested `Box` to represent the progress bar, filling the width based on the
 *   provided `progress` value.
 * - Displays a banana image at the end of the progress bar.
 *
 * **Usage:**
 * Use this composable function to display a custom linear progress indicator in your
 * Jetpack Compose UI.
 *
 *
 **/

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