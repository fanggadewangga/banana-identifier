package com.fangga.core.components.common

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.fangga.core.resource.greenPrimary
import com.fangga.core.utils.noRippleClickable

/**
 * **Composable Function:** AppButton
 *
 * **Purpose:**
 * A custom button composable that provides a flexible and customizable button UI.
 * This function allows you to define the button's appearance, behavior, and content.
 *
 * **Parameters:**
 * - `modifier`: Modifier for the layout of the button.
 * - `onClick`: A lambda function that is called when the button is clicked.
 * - `backgroundColor`: The background color of the button when enabled.
 * - `disabledBackgroundColor`: The background color of the button when disabled.
 * - `rippleColor`: The color of the ripple effect when the button is clicked.
 * - `enabled`: A boolean indicating whether the button is enabled or disabled.
 * - `borderWidth`: The width of the button's border.
 * - `borderColor`: The color of the button's border.
 * - `shape`: The shape of the button's corners.
 * - `contentAlignment`: The alignment of the button's content.
 * - `contentPadding`: Padding values for the button's content.
 * - `content`: A composable lambda that defines the content of the button.
 *
 * **Functionality:**
 * - Creates a `Box` to act as the button's container.
 * - Uses a `clip` modifier to apply the specified shape to the button.
 * - Uses a `border` modifier to add a border to the button.
 * - Uses a `background` modifier to set the button's background color based on its enabled state.
 * - Uses an `onSizeChanged` modifier to track the size of the button's content.
 * - Uses a `noRippleClickable` modifier to handle clicks and disable the ripple effect.
 * - Provides a `content` composable lambda to define the button's content.
 *
 * **Usage:**
 * Use this composable function to create custom buttons with specific styling and behavior.
 *
 * **Example:**
 *
 **/

@Composable
fun AppButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    backgroundColor: Color = greenPrimary,
    disabledBackgroundColor: Color = Color.Gray,
    rippleColor: Color = Color.White,
    enabled: Boolean = true,
    borderWidth: Dp = 0.dp,
    borderColor: Color = Color.Unspecified,
    shape: Shape = RoundedCornerShape(100.dp),
    contentAlignment: Alignment = Alignment.Center,
    contentPadding: PaddingValues = PaddingValues(12.dp),
    content: @Composable () -> Unit,
) {
    val contentHeight = remember { mutableStateOf(0.dp) }
    val contentWidth = remember { mutableStateOf(0.dp) }
    val localDensity = LocalDensity.current

    Box(contentAlignment = Alignment.Center) {
        Box(
            modifier = modifier
                .clip(shape)
                .size(width = contentWidth.value, height = contentHeight.value)
                .border(width = borderWidth, color = borderColor, shape = shape)
                .background(if (enabled) backgroundColor else disabledBackgroundColor)
        )

        Box(
            modifier = modifier
                .onSizeChanged {
                    contentWidth.value = with(localDensity) { it.width.toDp() }
                    contentHeight.value = with(localDensity) { it.height.toDp() }
                }
                .padding(contentPadding),
            contentAlignment = contentAlignment
        ) {
            content()
        }

        Box(
            modifier = modifier
                .clip(shape)
                .size(width = contentWidth.value, height = contentHeight.value)
                .noRippleClickable(enabled, onClick)
        )
    }
}