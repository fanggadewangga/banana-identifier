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

/**
 * **Composable Function:** AppTopBar
 *
 * **Purpose:**
 * A custom top app bar composable that displays a title and a back button.
 * This function provides a way to create a consistent top app bar with a back
 * navigation option.
 *
 * **Parameters:**
 * - `modifier`: Modifier for the layout of the top bar.
 * - `title`: The title text to display in the top bar.
 * - `onNavigateBack`: A lambda function that is called when the back button is clicked.
 *
 * **Functionality:**
 * - Creates a `Box` to act as the top bar's container.
 * - Sets the background color of the top bar to white.
 * - Displays a back button using the `AppImage` composable.
 * - Handles clicks on the back button using the `noRippleClickable` modifier.
 * - Displays the title text using the `AppText` composable.
 *
 * **Usage:**
 * Use this composable function to create a custom top app bar in your Jetpack Compose UI.
 *
 **/

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