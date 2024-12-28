package com.fangga.scan.presentation

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.widget.Toast
import androidx.camera.core.ImageCapture.OnImageCapturedCallback
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.ImageProxy
import androidx.camera.view.LifecycleCameraController
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewModelScope
import com.fangga.core.data.base.Resource
import com.fangga.core.data.datasource.LocalDataSource
import com.fangga.core.data.source.room.entity.ScanResultEntity
import com.fangga.core.navigation.NavigationService
import com.fangga.core.presentation.BaseViewModel
import com.fangga.core.utils.Constants.LATEST_RESULT_ID
import com.fangga.core.utils.converterBitmapToString
import com.fangga.core.utils.getBitmapFromUri
import com.fangga.core.utils.mapDateToFormattedString
import com.fangga.core.utils.saveBitmapToFileAndGetUri
import com.fangga.core.utils.toDescription
import com.fangga.scan.data.TfLiteClassifier
import com.fangga.scan.presentation.event.ScanEvent
import com.fangga.scan.presentation.state.ScanState
import com.fangga.scan.util.Constants
import com.fangga.scan.util.getRotationDegreesFromUri
import com.fangga.scan.util.hasRequiredPermission
import com.fangga.scan.util.rotateBitmap
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

/**
 * **Class:** ScanViewModel
 *
 * **Purpose:**
 * A ViewModel class that manages the state and logic for the scan screen. It
 * handles camera permissions, image capture, image analysis, and navigation
 * between screens.
 *
 * **Annotations:**
 * - `@HiltViewModel`: Indicates that this class is a Hilt ViewModel, which
 *   enables dependency injection.
 *
 * **Parameters:**
 * - `localDataSource`: An instance of `LocalDataSource` for accessing local data.
 * - `navigator`: An instance of `NavigationService` for navigating between
 *   screens.
 *
 * **Inheritance:**
 * - Extends `BaseViewModel<ScanState, ScanEvent>`, which provides a base
 *   implementation for managing UI state and events.
 *
 * **Functionality:**
 * - Checks for camera permissions.
 * - Opens or closes the gallery.
 * - Opens or closes the help dialog.
 * - Navigates back to the previous screen.
 * - Navigates to the scan result screen.
 * - Saves the latest scan result to the local data source.
 * - Takes a picture using the camera.
 * - Picks an image from the gallery.
 * - Opens or closes the bottom sheet.
 * - Shows or hides the loading indicator.
 * - Analyzes the captured image using a TensorFlow Lite classifier.
 * - Manages the UI state using the `ScanState` data class.
 * - Handles events using the `ScanEvent` sealed class.
 *
 * **Methods:**
 * - `checkCameraPermission(context: Context)`: Checks for camera permissions
 *   and requests them if necessary.
 * - `openGallery(isOpen: Boolean)`: Opens or closes the gallery.
 * - `openHelp(isOpen: Boolean)`: Opens or closes the help dialog.
 * - `navigateBack()`: Navigates back to the previous screen.
 * - `navigateToScanResult(context: Context, bananaType: String, ripenessType: String)`:
 *   Navigates to the scan result screen with the classification result.
 * - `saveLatestScanResult()`: Saves the latest scan result to the local data
 *   source.
 * - `takePicture(context: Context, controller: LifecycleCameraController)`:
 *   Takes a picture using the camera and analyzes it.
 * - `pickImageFromGallery(context: Context, uri: Uri)`: Picks an image from
 *   the gallery and analyzes it.
 * - `openSheet(isOpen: Boolean)`: Opens or closes the bottom sheet.
 * - `showLoading(isLoading: Boolean)`: Shows or hides the loading indicator.
 * - `analyzeCapturedImage(context: Context, bitmap: Bitmap)`: Analyzes the
 *   captured image using a TensorFlow Lite classifier.
 * - `handleEvent(event: ScanEvent)`: Handles the different events triggered
 *   by the UI.
 *
 * **Usage:**
 * Use this class as the ViewModel for the scan screen in your application.
 */

@HiltViewModel
class ScanViewModel @Inject constructor(
    private val localDataSource: LocalDataSource,
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

    private fun navigateToScanResult(
        context: Context,
        bananaType: String,
        ripenessType: String
    ) {
        val capturedImage = uiState.value.capturedImage
        if (capturedImage != null) {
            val capturedImageFile = saveBitmapToFileAndGetUri(context, capturedImage)
            val encodedImageUri = Uri.encode(capturedImageFile)

            navigator.navigateTo("scan_result/true/${LATEST_RESULT_ID}/${encodedImageUri}/${bananaType}/${ripenessType}") {
                launchSingleTop = true
                restoreState = true
            }
        }
    }

    @SuppressLint("NewApi")
    private fun saveLatestScanResult() {
        val classificationResult = uiState.value.scanResult
        val capturedImage = uiState.value.capturedImage

        if (classificationResult != null && capturedImage != null) {
            val date = LocalDateTime.now()
            val imageAsString = converterBitmapToString(capturedImage)

            val scanResult = ScanResultEntity(
                resultId = LATEST_RESULT_ID,
                bananaType = classificationResult.bananaType.toDescription(),
                ripenessType = classificationResult.ripenessType.toDescription(),
                image = imageAsString,
                timestamp = mapDateToFormattedString(date = date)
            )

            viewModelScope.launch {
                localDataSource.insertNewScanResult(scanResult).collectLatest { result ->
                    when (result) {
                        is Resource.Empty -> {
                            updateUiState { copy(isLoading = false) }
                        }

                        is Resource.Error -> {
                            updateUiState { copy(isLoading = false) }
                        }

                        is Resource.Loading -> updateUiState { copy(isLoading = true) }
                        is Resource.Success -> {
                            updateUiState { copy(isLoading = false) }
                        }
                    }
                }
            }
        }
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

                    val bitmap = image.toBitmap()
                    val rotationDegrees = image.imageInfo.rotationDegrees

                    val correctedBitmap = rotateBitmap(bitmap, rotationDegrees)

                    updateUiState { copy(capturedImage = correctedBitmap) }
                    analyzeCapturedImage(context, correctedBitmap)
                }

                override fun onError(exception: ImageCaptureException) {
                    super.onError(exception)
                    Toast.makeText(context, exception.toString(), Toast.LENGTH_SHORT).show()
                    updateUiState { copy(isError = true, errorMessage = exception.toString()) }
                }
            }
        )
    }

    private fun pickImageFromGallery(context: Context, uri: Uri) {
        updateUiState { copy(isLoading = true) }
        openSheet(true)

        val pickedImage = getBitmapFromUri(context, uri)
        val rotationDegrees = getRotationDegreesFromUri(context, uri)

        val correctedBitmap = rotateBitmap(pickedImage, rotationDegrees)
        updateUiState { copy(capturedImage = correctedBitmap) }

        analyzeCapturedImage(context, correctedBitmap)
    }

    private fun openSheet(isOpen: Boolean) {
        updateUiState { copy(isSheetOpen = isOpen) }
    }

    private fun showLoading(isLoading: Boolean) {
        updateUiState { copy(isLoading = isLoading, isSheetOpen = isLoading) }
    }

    private fun analyzeCapturedImage(
        context: Context,
        bitmap: Bitmap,
    ) {
        try {
            viewModelScope.launch {
                val classifier = TfLiteClassifier(context = context)
                val result = classifier.classify(bitmap)
                updateUiState { copy(scanResult = result) }
                saveLatestScanResult()

                delay(4000L)
                updateUiState { copy(isLoading = false) }

                delay(2000L)
                openSheet(false)

                if (result != null)
                    navigateToScanResult(
                        context,
                        result.bananaType.toDescription(),
                        result.ripenessType.toDescription()
                    )
                else
                    Toast.makeText(context, "Gagal melakukan scan", Toast.LENGTH_SHORT).show()

                updateUiState { copy(capturedImage = null) }
            }
        } catch (e: Exception) {
            Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    override suspend fun handleEvent(event: ScanEvent) {
        when (event) {
            is ScanEvent.CheckCameraPermission -> checkCameraPermission(event.context)
            is ScanEvent.OpenGallery -> {
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

            is ScanEvent.PickImageFromGallery -> pickImageFromGallery(
                event.context,
                event.pickedImage
            )

            is ScanEvent.OpenSheet -> openSheet(event.isOpen)
            is ScanEvent.ShowLoading -> showLoading(event.isLoading)
            is ScanEvent.UseFlash -> updateUiState { copy(isFlashOn = event.isFlashOn) }
        }
    }
}