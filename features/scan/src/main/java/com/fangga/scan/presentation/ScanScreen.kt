package com.fangga.scan.presentation

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.ImageCapture
import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.fangga.scan.presentation.component.ScanContent
import com.fangga.scan.presentation.event.ScanEvent

@Composable
fun ScanScreen(screenHeight: Int, screenWidth: Int) {

    val viewModel = hiltViewModel<ScanViewModel>()
    val state = viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val cameraController = remember {
        LifecycleCameraController(context).apply {
            setEnabledUseCases(CameraController.IMAGE_CAPTURE)
            imageCaptureMode = ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY
        }
    }
    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            if (uri != null) {
                viewModel.onEvent(ScanEvent.PickImageFromGallery(context, uri))
            }
        }
    )

    LaunchedEffect(true) {
        viewModel.onEvent(ScanEvent.CheckCameraPermission(context))
    }

    ScanContent(
        cameraController = cameraController,
        capturedImage = state.value.capturedImage,
        isLoading = state.value.isLoading,
        isSheetOpen = state.value.isSheetOpen,
        isFlashOn = state.value.isFlashOn,
        isHelpOpen = state.value.isOpenHelp,
        onBackClicked = { viewModel.onEvent(ScanEvent.NavigateBack) },
        screenWidth = screenWidth,
        screenHeight = screenHeight,
        onGalleryClicked = {
            viewModel.onEvent(ScanEvent.OpenGallery(true))
            photoPickerLauncher.launch(
                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
            )
        },
        onTorchClicked = { isFlashOn ->
            viewModel.onEvent(ScanEvent.UseFlash(isFlashOn))
        },
        onHelpClicked = { viewModel.onEvent(ScanEvent.OpenHelp(true)) },
        onCaptureImage = {
            viewModel.onEvent(
                ScanEvent.TakePicture(
                    context = context,
                    controller = cameraController,
                )
            )
        },
        onSheetDismissed = { viewModel.onEvent(ScanEvent.OpenHelp(false)) },
        modifier = Modifier.fillMaxSize()
    )
}