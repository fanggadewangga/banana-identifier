package com.fangga.core.data.model.result

import android.net.Uri

data class ScanResult(
    var resultId: String,
    val bananaType: String,
    val ripenessType: String,
    val image: Uri,
    val timestamp: String,
)
