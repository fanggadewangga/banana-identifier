package com.fangga.scan.presentation.component

import android.graphics.Bitmap
import androidx.camera.core.CameraSelector
import androidx.camera.view.LifecycleCameraController
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.fangga.core.components.common.AppText
import com.fangga.core.components.feature.ScanHelper
import com.fangga.core.resource.h11SemiBold
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScanContent(
    modifier: Modifier = Modifier,
    capturedImage: Bitmap?,
    isSheetOpen: Boolean,
    isLoading: Boolean,
    isFlashOn: Boolean,
    isHelpOpen: Boolean,
    cameraController: LifecycleCameraController,
    screenHeight: Int,
    screenWidth: Int,
    onBackClicked: () -> Unit,
    onGalleryClicked: () -> Unit,
    onTorchClicked: (Boolean) -> Unit,
    onHelpClicked: () -> Unit,
    onCaptureImage: () -> Unit,
    onSheetDismissed: () -> Unit,
) {

    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val scope = rememberCoroutineScope()

    Box(modifier = modifier) {
        AnimatedVisibility(visible = capturedImage == null) {
            CameraPreview(controller = cameraController, modifier = Modifier.fillMaxSize())
        }
        AnimatedVisibility(visible = capturedImage != null) {
            capturedImage?.let { bitmap ->
                Image(
                    bitmap = bitmap.asImageBitmap(),
                    contentDescription = "Captured image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
        TopCameraMenu(
            onBackClicked = { onBackClicked() },
            onFlashClicked = {
                cameraController.enableTorch(!isFlashOn)
                onTorchClicked(!isFlashOn)
            },
            onFlipClicked = {
                cameraController.cameraSelector =
                    if (cameraController.cameraSelector == CameraSelector.DEFAULT_BACK_CAMERA) {
                        CameraSelector.DEFAULT_FRONT_CAMERA
                    } else {
                        CameraSelector.DEFAULT_BACK_CAMERA
                    }
            },
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
                .padding(
                    vertical = (screenHeight * 0.1f).dp,
                    horizontal = (screenWidth * 0.05).dp
                )
        )
        ScanFrame(
            screenHeight = screenHeight,
            screenWidth = screenWidth,
            isSheetOpen = isSheetOpen,
            modifier = Modifier
                .height((screenHeight * 0.75).dp)
                .align(Alignment.Center)
        )
        BottomCameraMenu(
            onGalleryClicked = onGalleryClicked,
            onHelpClicked = onHelpClicked,
            onCaptureImage = onCaptureImage,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .navigationBarsPadding()
        )


        if (isSheetOpen) {
            ModalBottomSheet(
                containerColor = Color.White,
                sheetState = sheetState,
                onDismissRequest = {
                    onSheetDismissed()
                    scope.launch {
                        sheetState.hide()
                    }
                },
                modifier = Modifier.wrapContentSize()
            ) {
                if (isHelpOpen)
                    ScanHelper(
                        helperHeader = {
                            AppText(
                                text = "Bantuan",
                                textStyle = h11SemiBold,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth()
                            )
                        },
                        backgroundColor = Color.White,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    )
                else {
                    ScanLoading(
                        isLoading = isLoading,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    )
                }
            }
        }
    }
}