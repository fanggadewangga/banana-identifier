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

/**
 * **Function:** MakeToast
 *
 * **Purpose:**
 * Configures and displays a custom Toast message using Jetpack Compose.
 *
 * **Parameters:**
 * - `modifier`: Modifier for the Toast's layout.
 * - `message`: The text message to display in the Toast.
 * - `duration`: The duration of the Toast (e.g., `Toast.LENGTH_SHORT`, `Toast.LENGTH_LONG`).
 * - `type`: An `AppToastProperty` enum value that determines the Toast's background and text colors.
 * - `paddingValues`: Padding values for the Toast's content.
 * - `contentAlignment`: Alignment of the Toast's content.
 *
 * **Functionality:**
 * - Creates a `ComposeView` to host the Compose UI.
 * - Sets the content of the `ComposeView` using `AppToastUtil.SetView`.
 * - Sets the `ViewTreeSavedStateRegistryOwner`, `ViewTreeLifecycleOwner`, and
 *   `ViewTreeViewModelStoreOwner` for proper lifecycle management.
 * - Sets the Toast's duration and view.
 *
 * **Composable Function:**
 * This is a composable function, meaning it can only be called within a composable scope.
 */

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