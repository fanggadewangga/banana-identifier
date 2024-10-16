package com.fangga.scan.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import com.fangga.core.components.common.AppImage
import com.fangga.core.resource.backIcon
import com.fangga.core.resource.cameraFlashIcon
import com.fangga.core.resource.cameraFlipIcon
import com.fangga.core.utils.noRippleClickable

@Composable
fun TopCameraMenu(
    modifier: Modifier = Modifier,
    onBackClicked: () -> Unit,
    onFlashClicked: () -> Unit,
    onFlipClicked: () -> Unit,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth()
    ) {
        AppImage(
            imageUrl = backIcon,
            contentDescription = "Back icon",
            colorFilter = ColorFilter.tint(color = Color.White),
            modifier = Modifier
                .size(24.dp)
                .noRippleClickable { onBackClicked() }
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AppImage(
                imageUrl = cameraFlashIcon,
                contentDescription = "Camera flash icon",
                modifier = Modifier
                    .size(24.dp)
                    .noRippleClickable { onFlashClicked() }
            )
            AppImage(
                imageUrl = cameraFlipIcon,
                contentDescription = "Camera flip icon",
                modifier = Modifier
                    .size(24.dp)
                    .noRippleClickable { onFlipClicked() }
            )
        }
    }
}