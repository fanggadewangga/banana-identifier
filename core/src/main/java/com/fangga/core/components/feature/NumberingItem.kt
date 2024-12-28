package com.fangga.core.components.feature

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

/**
 * **Composable Function:** NumberingItem
 *
 * **Purpose:**
 * A composable function that displays a numbered item with a circular background.
 * This function is useful for creating numbered lists or steps in a UI.
 *
 * **Parameters:**
 * - `modifier`: Modifier for the layout of the numbered item.
 * - `number`: The number to display as a string.
 * - `shape`: The shape of the background, defaults to a circle.
 * - `textColor`: The color of the text.
 * - `backgroundColor`: The background color of the circle.
 *
 * **Functionality:**
 * - Creates a `Box` to act as the container for the numbered item.
 * - Sets the background color and shape of the `Box`.
 * - Centers the content within the `Box`.
 * - Displays the number using the `AppText` composable.
 *
 * **Usage:**
 * Use this composable function to create numbering indicators
 **/


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