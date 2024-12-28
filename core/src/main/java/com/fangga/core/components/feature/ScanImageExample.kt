package com.fangga.core.components.feature

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.fangga.core.components.common.AppImage
import com.fangga.core.components.common.AppText
import com.fangga.core.data.model.enums.ScanExampleType
import com.fangga.core.resource.bodyText12Medium
import com.fangga.core.resource.scanExampleCorrectIcon
import com.fangga.core.resource.scanExampleIncorrectIcon

/**
 * **Composable Function:** ScanImageExample
 *
 * **Purpose:**
 * A composable function that displays an image example for scanning, along with
 * an optional description and an icon indicating whether the example is correct
 * or incorrect.
 *
 * **Parameters:**
 * - `modifier`: Modifier for the layout of the image example.
 * - `imageSource`: The drawable resource ID of the image to display.
 * - `description`: An optional description text to display below the image.
 * - `contentDescription`: A description of the image for accessibility purposes.
 * - `type`: An enum value of type `ScanExampleType` that determines whether the
 *   example is correct or incorrect.
 * - `imageHeight`: The height of the image.
 * - `imageWidth`: The width of the image.
 *
 * **Functionality:**
 * - Creates a `Column` to arrange the image and description vertically.
 * - Centers the content horizontally within the `Column`.
 * - Displays the image using the `AppImage` composable.
 * - Displays an icon indicating whether the example is correct or incorrect using
 *   the `AppImage` composable.
 * - Displays the description text using the `AppText` composable, if provided.
 *
 * **Usage:**
 * Use this composable function to display image examples for scanning in your
 * Jetpack Compose UI.
 **/


@Composable
fun ScanImageExample(
    modifier: Modifier = Modifier,
    @DrawableRes imageSource: Int,
    description: String? = null,
    contentDescription: String,
    type: ScanExampleType,
    imageHeight: Dp,
    imageWidth: Dp,
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = modifier) {
        Box {
            AppImage(
                imageUrl = imageSource,
                contentDescription = contentDescription,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(width = imageWidth, height = imageHeight)
            )
            AppImage(
                imageUrl = if (type == ScanExampleType.CORRECT) scanExampleCorrectIcon else scanExampleIncorrectIcon,
                contentDescription = "Scan Example Icon",
                modifier = Modifier
                    .padding(5.dp)
                    .size(24.dp)
                    .align(Alignment.TopEnd)
            )
        }
        if (description != null)
            AppText(text = description, textStyle = bodyText12Medium)
    }
}