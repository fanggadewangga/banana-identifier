package com.fangga.core.components.common.apptoast

import androidx.compose.ui.graphics.Color
import com.fangga.core.resource.redMainDanger
import com.fangga.core.resource.tosca100

class Error : AppToastProperty {
    override fun getBackgroundColor(): Color = redMainDanger

    override fun getBorderColor(): Color = redMainDanger

    override fun getTextColor(): Color = Color.White
}

class Success : AppToastProperty {
    override fun getBackgroundColor(): Color = tosca100

    override fun getBorderColor(): Color = tosca100

    override fun getTextColor(): Color = Color.White
}