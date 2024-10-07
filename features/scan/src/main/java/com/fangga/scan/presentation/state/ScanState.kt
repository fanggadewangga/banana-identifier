package com.fangga.scan.presentation.state

import android.graphics.Bitmap

data class ScanState(
    val capturedImage: Bitmap? = null,
    val errorMessage: String = "",
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val isOpenGallery: Boolean = false,
    val isOpenHelp: Boolean = false,
    val isFlashOn: Boolean = false,
    val isSheetOpen: Boolean = false,
)
