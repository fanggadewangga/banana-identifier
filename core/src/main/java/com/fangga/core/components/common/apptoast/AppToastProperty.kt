package com.fangga.core.components.common.apptoast

import androidx.compose.ui.graphics.Color

interface AppToastProperty {
    fun getBackgroundColor(): Color
    fun getBorderColor(): Color
    fun getTextColor(): Color
}