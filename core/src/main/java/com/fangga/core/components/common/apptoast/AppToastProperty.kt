package com.fangga.core.components.common.apptoast

import androidx.compose.ui.graphics.Color

/**
 * **Interface:** AppToastProperty
 *
 * **Purpose:**
 * Defines the contract for providing styling properties for custom Toast messages.
 * This interface is intended to be implemented by enums or classes that represent
 * different types of Toast messages (e.g., success, error, warning) and their
 * corresponding visual styles.
 *
 * **Methods:**
 * - `getBackgroundColor(): Color`: Returns the background color for the Toast.
 * - `getBorderColor(): Color`: Returns the border color for the Toast.
 * - `getTextColor(): Color`: Returns the text color for the Toast.
 *
 * **Usage:**
 * Implement this interface to create different Toast styles. The implementations
 * should provide the appropriate colors for the background, border, and text of
 * the Toast.
 *
 * **Example:**
 *
 **/

interface AppToastProperty {
    fun getBackgroundColor(): Color
    fun getBorderColor(): Color
    fun getTextColor(): Color
}