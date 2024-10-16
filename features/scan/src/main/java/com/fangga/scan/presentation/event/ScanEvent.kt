package com.fangga.scan.presentation.event

import android.content.Context
import android.net.Uri
import androidx.camera.view.LifecycleCameraController

sealed class ScanEvent {
    data class CheckCameraPermission(val context: Context) : ScanEvent()
    data class TakePicture(
        val context: Context,
        val controller: LifecycleCameraController,
    ) : ScanEvent()

    data class PickImageFromGallery(val context: Context, val pickedImage: Uri) : ScanEvent()
    data class ShowLoading(val isLoading: Boolean) : ScanEvent()
    data class OpenGallery(val isOpen: Boolean) : ScanEvent()
    data class OpenHelp(val isOpen: Boolean) : ScanEvent()
    data class OpenSheet(val isOpen: Boolean) : ScanEvent()
    data class UseFlash(val isFlashOn: Boolean) : ScanEvent()
    data object NavigateBack : ScanEvent()
}