package com.fangga.scan.data

import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.fangga.core.model.result.BananaClassificationResult
import com.fangga.scan.domain.BananaClassifier
import com.fangga.scan.presentation.utils.centerCrop

class BananaImageAnalyzer(
    private val classifier: BananaClassifier,
    private val onResult: (List<BananaClassificationResult>) -> Unit,
) : ImageAnalysis.Analyzer {
    private var frameSkipCounter = 0

    override fun analyze(image: ImageProxy) {

        if (frameSkipCounter % 60 == 0) {
            val rotationDegrees = image.imageInfo.rotationDegrees
            val bitmap = image
                .toBitmap()
                .centerCrop(321, 321)

            val result = classifier.classify(bitmap, rotationDegrees)
            onResult(result)
        }
        frameSkipCounter++

        image.close()
    }
}