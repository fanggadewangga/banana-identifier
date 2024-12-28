package com.fangga.core.components.feature

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.fangga.core.components.common.AppText
import com.fangga.core.resource.bodyText12SemiBold
import com.fangga.core.resource.greenPrimary

/**
 * **Composable Function:** PointItem
 *
 * **Purpose:**
 * A composable function that displays a point item with a numbered circle, a title,
 * and additional content. This function is useful for creating lists of points or
 * steps with a consistent visual structure.
 *
 * **Parameters:**
 * - `modifier`: Modifier for the layout of the point item.
 * - `number`: The number to display in the circle.
 * - `numberShape`: The shape of the number's background, defaults to a circle.
 * - `numberBackgroundColor`: The background color of the number's circle.
 * - `numberTextColor`: The color of the number text.
 * - `title`: The title text to display for the point item.
 * - `content`: A composable lambda that defines the additional content for the point item.
 *
 * **Functionality:**
 * - Creates a `Row` to arrange the numbered circle, title, and content horizontally.
 * - Uses the `NumberingItem` composable to display the numbered circle.
 * - Uses a `Column` to arrange the title and additional content vertically.
 * - Displays the title using the `AppText` composable.
 * - Provides a `content` composable lambda to define the additional content for the point item.
 *
 * **Usage:**
 * Use this composable function to create point items in your Jetpack Compose UI.
 **/

@Composable
fun PointItem(
    modifier: Modifier = Modifier,
    number: String,
    numberShape: Shape = CircleShape,
    numberBackgroundColor: Color = greenPrimary,
    numberTextColor: Color = Color.White,
    title: String,
    content: @Composable ColumnScope.() -> Unit,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(14.dp),
        modifier = modifier
    ) {
        NumberingItem(
            number = number,
            shape = numberShape,
            backgroundColor = numberBackgroundColor,
            textColor = numberTextColor,
            modifier = Modifier.size(32.dp)
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            AppText(text = title, textStyle = bodyText12SemiBold, color = Color.Black)
            content()
        }
    }
}