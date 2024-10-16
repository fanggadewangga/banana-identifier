package com.fangga.scan.domain

import android.graphics.Bitmap
import com.fangga.core.model.result.BananaClassificationResult

interface BananaClassifier {
    fun classify(bitmap: Bitmap, rotation: Int): List<BananaClassificationResult>
}