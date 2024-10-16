package com.fangga.scan.data

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import android.view.Surface
import com.fangga.core.data.model.result.BananaClassificationResult
import com.fangga.features.scan.ml.Bananaopt
import com.fangga.scan.domain.BananaClassification
import com.fangga.scan.domain.BananaClassifier
import com.fangga.scan.util.rotateBitmap
import com.fangga.scan.util.toClassificationResult
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.image.ImageProcessor
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.image.ops.ResizeOp
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import org.tensorflow.lite.task.core.vision.ImageProcessingOptions

class TfLiteClassifier(
    private val context: Context,
    private val threshold: Float = 0f,
) : BananaClassifier {

    init {
        setupModel()
    }

    private var model: Bananaopt? = null

    // Set up the TensorFlow Lite model
    private fun setupModel() {
        try {
            model = Bananaopt.newInstance(context)
        } catch (e: Exception) {
            Log.e("TfLiteClassifier", "Error loading model: ${e.message}")
            e.printStackTrace()
        }
    }

    override fun classify(bitmap: Bitmap, rotation: Int): List<BananaClassificationResult> {
        Log.d("TfLiteClassifier", "Classify called")

        val correctedBitmap = rotateBitmap(bitmap, rotation)

        // Process the image
        val imageProcessor = ImageProcessor.Builder()
            .add(ResizeOp(224, 224, ResizeOp.ResizeMethod.BILINEAR))
            .build()

        val tensorImage = TensorImage(DataType.FLOAT32)
        tensorImage.load(correctedBitmap)

        val processedTensorImage = imageProcessor.process(tensorImage) // Proses TensorImage

        // Convert image to ByteBuffer
        val byteBuffer = processedTensorImage.buffer

        // Prepare input tensor
        val inputFeature0 =
            TensorBuffer.createFixedSize(intArrayOf(1, 224, 224, 3), DataType.FLOAT32)
        inputFeature0.loadBuffer(byteBuffer)

        Log.d("Classifier", "inputFeature0: $inputFeature0")

        // Perform inference
        val outputs = model?.process(inputFeature0)
        val outputFeature0 = outputs?.outputFeature0AsTensorBuffer

        // Process the results
        val results = mutableListOf<BananaClassificationResult>()
        val bestResult = outputFeature0?.let { result ->
            val scores = result.floatArray
            val maxIndex = scores.indices.maxByOrNull { scores[it] } // Find index of max score

            // Log all results
            for (i in scores.indices) {
                val score = scores[i]
                val classification = BananaClassification(
                    name = "Class $i",
                    score = score
                ).toClassificationResult()
                results.add(classification)
                Log.d("TfLiteClassifier", "Result: $classification")
            }

            if (maxIndex != null && scores[maxIndex] >= threshold) {
                BananaClassification(
                    name = "Class $maxIndex",
                    score = scores[maxIndex]
                ).toClassificationResult()
            } else {
                null
            }
        }

        Log.d("TfLiteClassifier", "Best Result: $bestResult")

        // Release resources
        model?.close()

        return results.distinctBy { it.bananaType.name }
    }

    private fun getOrientationFromRotation(rotation: Int): ImageProcessingOptions.Orientation {
        return when (rotation) {
            Surface.ROTATION_270 -> ImageProcessingOptions.Orientation.BOTTOM_RIGHT
            Surface.ROTATION_90 -> ImageProcessingOptions.Orientation.TOP_LEFT
            Surface.ROTATION_180 -> ImageProcessingOptions.Orientation.RIGHT_BOTTOM
            else -> ImageProcessingOptions.Orientation.RIGHT_TOP
        }
    }
}