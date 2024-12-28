package com.fangga.core.components.common.apptoast

import androidx.compose.ui.graphics.Color
import com.fangga.core.resource.redMainDanger
import com.fangga.core.resource.tosca100

/**
 * **Class:** Error
 *
 * **Purpose:**
 * Provides styling for error-type Toast messages.
 *
 * **Styling:**
 * - Background: `redMainDanger`
 * - Border: `redMainDanger`
 * - Text: `Color.White`
 *
 * **Usage:**
 * Use this class to style error Toasts.
 *
 * **Note:**
 * `redMainDanger` is assumed to be defined elsewhere.
 */

class Error : AppToastProperty {
    override fun getBackgroundColor(): Color = redMainDanger

    override fun getBorderColor(): Color = redMainDanger

    override fun getTextColor(): Color = Color.White
}

/**
 * **Class:** Success
 *
 * **Purpose:**
 * Provides styling for success-type Toast messages.
 *
 * **Styling:**
 * - Background: `tosca100`
 * - Border: `tosca100`
 * - Text: `Color.White`
 *
 * **Usage:**
 * Use this class to style success Toasts.
 *
 * **Note:**
 * `tosca100` is assumed to be defined elsewhere.
 */

class Success : AppToastProperty {
    override fun getBackgroundColor(): Color = tosca100

    override fun getBorderColor(): Color = tosca100

    override fun getTextColor(): Color = Color.White
}