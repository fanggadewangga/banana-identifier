package com.fangga.core.components.common.apptoast

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSavedStateRegistryOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.setViewTreeLifecycleOwner
import androidx.lifecycle.setViewTreeViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.savedstate.setViewTreeSavedStateRegistryOwner

class AppToast(context: Context) : Toast(context) {
    @Composable
    fun MakeToast(
        modifier: Modifier = Modifier,
        message: String,
        duration: Int = LENGTH_SHORT,
        type: AppToastProperty,
        paddingValues: PaddingValues,
        contentAlignment: Alignment,
    ) {
        val context = LocalContext.current
        val views = ComposeView(context)

        views.setContent {
            AppToastUtil.SetView(
                message = message,
                backgroundColor = type.getBackgroundColor(),
                textColor = type.getTextColor(),
                padding = paddingValues,
                contentAlignment = contentAlignment
            )
        }

        views.setViewTreeSavedStateRegistryOwner(LocalSavedStateRegistryOwner.current)
        views.setViewTreeLifecycleOwner(LocalLifecycleOwner.current)
        views.setViewTreeViewModelStoreOwner(LocalViewModelStoreOwner.current)



        this.duration = duration
        this.view = views
    }
}