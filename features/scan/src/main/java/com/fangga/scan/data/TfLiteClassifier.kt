package com.fangga.scan.data

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import com.fangga.core.data.model.result.BananaClassificationResult
import com.fangga.features.scan.ml.Mobilenet
import com.fangga.scan.domain.BananaClassification
import com.fangga.scan.domain.BananaClassifier
import com.fangga.scan.util.rotateBitmap
import com.fangga.scan.util.toClassificationResult
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.common.ops.NormalizeOp
import org.tensorflow.lite.support.image.ImageProcessor
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.image.ops.ResizeOp
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer

class TfLiteClassifier(
    private val context: Context,
    private val threshold: Float = 0f,
) : BananaClassifier {

    init {
        setupModel()
    }

    private var model: Mobilenet? = null

    private fun setupModel() {
        try {
            model = Mobilenet.newInstance(context)
        } catch (e: Exception) {
            Log.e("TfLiteClassifier", "Error loading model: ${e.message}")
            e.printStackTrace()
        }
    }

    override fun classify(bitmap: Bitmap, rotation: Int): List<BananaClassificationResult> {
        val correctedBitmap = rotateBitmap(bitmap, rotation)

        // Process the image
        val imageProcessor = ImageProcessor.Builder()
            .add(ResizeOp(240, 240, ResizeOp.ResizeMethod.NEAREST_NEIGHBOR))
            .add(NormalizeOp(0f, 1f))
            .build()

        val tensorImage = TensorImage(DataType.FLOAT32)
        tensorImage.load(correctedBitmap)

        val processedTensorImage = imageProcessor.process(tensorImage)

        // Convert image to ByteBuffer
        val byteBuffer = processedTensorImage.buffer

        val inputFeature0 =
            TensorBuffer.createFixedSize(intArrayOf(1, 240, 240, 3), DataType.FLOAT32)
        inputFeature0.loadBuffer(byteBuffer)

        val outputs = model?.process(inputFeature0)
        val outputFeature0 = outputs?.outputFeature0AsTensorBuffer

        // Process the results
        val results = mutableListOf<BananaClassificationResult>()
        outputFeature0?.let { result ->
            val scores = result.floatArray
            val maxResult = scores.indices.maxByOrNull { scores[it] } // Index of max score

            if (maxResult != null && scores[maxResult] >= threshold) {
                val bestClassification = BananaClassification(
                    name = "Class $maxResult",
                    score = scores[maxResult]
                ).toClassificationResult()

                // Log the best result
                Log.d("TfLiteClassifier", "Best classification: $bestClassification")
                model?.close()
                return listOf(bestClassification)
            }
        }

        model?.close()
        return emptyList()
    }
}