package com.fangga.core.components.common

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow

/**
 * **Composable Function:** AppText
 *
 * **Purpose:**
 * A composable function that displays text with customizable styling.
 * This function is a wrapper around the standard `Text` composable, providing
 * a convenient way to display text with specific styling and formatting.
 *
 * **Parameters:**
 * - `modifier`: Modifier for the layout of the text.
 * - `text`: The text string to display.
 * - `textAlign`: The alignment of the text within its bounds.
 * - `color`: The color of the text.
 * - `textStyle`: The style of the text (e.g., font, size, weight).
 * - `overflow`: How to handle text that overflows its bounds.
 * - `maxLine`: The maximum number of lines to display.
 *
 * **Functionality:**
 * - Uses the standard `Text` composable to display the provided text string.
 * - Applies the specified `modifier`, `textAlign`, `color`, `textStyle`, `overflow`,
 *   and `maxLine` parameters to the `Text` composable.
 *
 * **Usage:**
 * Use this composable function to display text in your Jetpack Compose UI.
 *
 **/

@Composable
fun AppText(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign = TextAlign.Start,
    color: Color = Color.Black,
    textStyle: TextStyle = TextStyle.Default,
    overflow: TextOverflow = TextOverflow.Visible,
    maxLine: Int = Int.MAX_VALUE,
) {
    Text(
        modifier = modifier,
        text = text,
        textAlign = textAlign,
        color = color,
        style = textStyle,
        overflow = overflow,
        maxLines = maxLine
    )
}