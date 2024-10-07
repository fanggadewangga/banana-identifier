package com.fangga.scan.presentation

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import androidx.camera.core.ImageCapture.OnImageCapturedCallback
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.ImageProxy
import androidx.camera.view.LifecycleCameraController
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewModelScope
import com.fangga.core.navigation.NavigationService
import com.fangga.core.presentation.BaseViewModel
import com.fangga.scan.presentation.event.ScanEvent
import com.fangga.scan.presentation.state.ScanState
import com.fangga.scan.util.Constants
import com.fangga.scan.util.hasRequiredPermission
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScanViewModel @Inject constructor(
    private val navigator: NavigationService
) : BaseViewModel<ScanState, ScanEvent>(ScanState()) {

    private fun checkCameraPermission(context: Context) {
        if (!hasRequiredPermission(context)) {
            ActivityCompat.requestPermissions(
                context as Activity,
                Constants.CAMERAX_PERMISSION,
                0
            )
        }
    }

    private fun openGallery(isOpen: Boolean) {
        updateUiState { copy(isOpenGallery = isOpen) }
    }

    private fun openHelp(isOpen: Boolean) {
        updateUiState { copy(isOpenHelp = isOpen) }
    }

    private fun navigateBack() {
        navigator.goBack()
    }

    private fun takePicture(
        context: Context,
        controller: LifecycleCameraController,
    ) {
        updateUiState { copy(isLoading = true) }
        openSheet(true)
        val executor = ContextCompat.getMainExecutor(context)

        controller.takePicture(
            executor,
            object : OnImageCapturedCallback() {
                override fun onCaptureSuccess(image: ImageProxy) {
                    super.onCaptureSuccess(image)
                    updateUiState { copy(capturedImage = image.toBitmap()) }
                    Log.d("ScanViewModel", "takePicture: $image")
                }

                override fun onError(exception: ImageCaptureException) {
                    super.onError(exception)
                    Log.d("ScanViewModel", "onError: $exception")
                    updateUiState { copy(isError = true, errorMessage = exception.toString()) }
                }
            }
        )

        viewModelScope.launch {
            delay(4000L)
            updateUiState { copy(isLoading = false) }
            delay(2000L)
            openSheet(false)
        }
    }

    private fun pickImage(pickedImage: Bitmap) {
        updateUiState { copy(capturedImage = pickedImage) }
        Log.d("ScanViewModel", "pickImage: $pickedImage")
    }

    private fun openSheet(isOpen: Boolean) {
        updateUiState { copy(isSheetOpen = isOpen) }
    }

    private fun showLoading(isLoading: Boolean) {
        updateUiState { copy(isLoading = isLoading, isSheetOpen = isLoading) }
    }

    override suspend fun handleEvent(event: ScanEvent) {
        when (event) {
            is ScanEvent.CheckCameraPermission -> checkCameraPermission(event.context)
            is ScanEvent.OpenGallery -> {
                openSheet(event.isOpen)
                openGallery(event.isOpen)
            }
            is ScanEvent.OpenHelp -> {
                openSheet(event.isOpen)
                openHelp(event.isOpen)
            }

            ScanEvent.NavigateBack -> navigateBack()
            is ScanEvent.TakePicture -> takePicture(
                event.context,
                event.controller,
            )

            is ScanEvent.PickImage -> pickImage(event.pickedImage)
            is ScanEvent.OpenSheet -> openSheet(event.isOpen)
            is ScanEvent.ShowLoading -> showLoading(event.isLoading)
        }
    }
}