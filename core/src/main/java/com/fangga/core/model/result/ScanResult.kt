package com.fangga.core.model.result

import android.net.Uri

data class ScanResult(
    val image: Uri,
    val resultId: String,
    val bananaType: String,
    val ripenessType: String,
    val timestamp: String,
)
