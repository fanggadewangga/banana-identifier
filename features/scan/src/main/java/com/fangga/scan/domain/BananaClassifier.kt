package com.fangga.scan.domain

import android.graphics.Bitmap
import com.fangga.core.data.model.result.BananaClassificationResult

interface BananaClassifier {
    fun classify(bitmap: Bitmap): BananaClassificationResult?
}