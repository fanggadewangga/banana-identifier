package com.fangga.scan.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.fangga.core.components.common.AppImage
import com.fangga.core.resource.cameraShutterIcon
import com.fangga.core.resource.galleryIcon
import com.fangga.core.resource.greenPrimary
import com.fangga.core.resource.helpIcon
import com.fangga.core.utils.noRippleClickable

@Composable
fun BottomCameraMenu(
    modifier: Modifier = Modifier,
    onGalleryClicked: () -> Unit,
    onHelpClicked: () -> Unit,
    onCaptureImage: () -> Unit,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxWidth()
            .background(color = Color.White)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 32.dp)
        ) {
            CameraMenu(icon = galleryIcon, title = "Galeri", onClick = onGalleryClicked)
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(64.dp)
                    .background(color = greenPrimary, shape = CircleShape)
                    .noRippleClickable { onCaptureImage() }
            ) {
                AppImage(
                    imageUrl = cameraShutterIcon,
                    contentDescription = "Camera shutter icon",
                    modifier = Modifier.size(28.dp)
                )
            }
            CameraMenu(icon = helpIcon, title = "Bantuan", onClick = onHelpClicked)
        }
    }
}