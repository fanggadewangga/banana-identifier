package com.fangga.core.data.model.result

import android.graphics.Bitmap

data class ScanResultList(
    val image: Bitmap,
    val resultId: String,
    val bananaType: String,
    val ripenessType: String,
    var isActionRevealed: Boolean = false,
    val timestamp: String,
)
