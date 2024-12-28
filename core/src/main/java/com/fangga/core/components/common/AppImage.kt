package com.fangga.core.components.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage

/**
 * **Composable Function:** AppImage
 *
 * **Purpose:**
 * A composable function that displays an image using the Coil library's `AsyncImage`.
 * This function simplifies the process of loading and displaying images from various sources,
 * such as URLs, drawables, and file paths.
 *
 * **Parameters:**
 * - `imageUrl`: The source of the image. Can be a URL, drawable resource ID, file path, etc.
 * - `modifier`: Modifier for the layout of the image.
 * - `contentScale`: How the image should be scaled to fit its bounds.
 * - `contentDescription`: A description of the image for accessibility purposes.
 * - `colorFilter`: An optional color filter to apply to the image.
 *
 * **Functionality:**
 * - Uses the Coil library's `AsyncImage` composable to load and display the image.
 * - Passes the provided `imageUrl`, `contentDescription`, `contentScale`, `colorFilter`,
 *   and `modifier` parameters to the `AsyncImage` composable.
 *
 * **Usage:**
 * Use this composable function to display images in your Jetpack Compose UI.
 *
 **/

@Composable
fun AppImage(
    imageUrl: Any,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Fit,
    contentDescription: String,
    colorFilter: ColorFilter? = null
) {
    AsyncImage(
        model = imageUrl,
        contentDescription = contentDescription,
        contentScale = contentScale,
        colorFilter = colorFilter,
        modifier = modifier
    )
}