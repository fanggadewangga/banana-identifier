package com.fangga.scan.data

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import com.fangga.core.data.model.result.BananaClassificationResult
import com.fangga.features.scan.ml.Mobilenet
import com.fangga.scan.domain.BananaClassification
import com.fangga.scan.domain.BananaClassifier
import com.fangga.scan.util.toClassificationResult
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.common.ops.NormalizeOp
import org.tensorflow.lite.support.image.ImageProcessor
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.image.ops.ResizeOp
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer

/**
 * **Class:** TfLiteClassifier
 *
 * **Purpose:**
 * A class that uses a TensorFlow Lite model to classify banana images. This
 * class loads a pre-trained model and processes input images to provide
 * classification results.
 *
 * **Parameters:**
 * - `context`: The application context, used to access the model file.
 *
 * **Interfaces:**
 * - Implements the `BananaClassifier` interface, which defines the contract
 *   for banana classification.
 *
 * **Functionality:**
 * - Loads a TensorFlow Lite model from the assets folder.
 * - Preprocesses input images by resizing and normalizing them.
 * - Runs the model on the preprocessed image.
 * - Processes the output of the model to provide a classification result.
 *
 * **Methods:**
 * - `setupModel()`: Loads the TensorFlow Lite model from the assets folder.
 * - `classify(bitmap: Bitmap)`: Classifies a given bitmap image and returns
 *   the classification result.
 *
 * **Usage:**
 * Use this class to classify banana images in your application.
 */

class TfLiteClassifier(
    private val context: Context,
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

    override fun classify(bitmap: Bitmap): BananaClassificationResult? {
        // Process the image
        val imageProcessor = ImageProcessor.Builder()
            .add(ResizeOp(240, 240, ResizeOp.ResizeMethod.BILINEAR))
            .add(NormalizeOp(127.5f, 127.5f))
            .build()

        val tensorImage = TensorImage(DataType.FLOAT32)
        tensorImage.load(bitmap)

        val processedTensorImage = imageProcessor.process(tensorImage)

        // Convert image to ByteBuffer
        val byteBuffer = processedTensorImage.buffer

        val inputFeature0 =
            TensorBuffer.createFixedSize(intArrayOf(1, 240, 240, 3), DataType.FLOAT32)
        inputFeature0.loadBuffer(byteBuffer)

        val outputs = model?.process(inputFeature0)
        val outputFeature0 = outputs?.outputFeature0AsTensorBuffer

        var bestResult: BananaClassificationResult? = null

        // Process the results
        outputFeature0?.let { result ->
            val scores = result.floatArray

            val results = scores
                .mapIndexed { index, score ->
                    val className = "Class $index"
                    BananaClassification(className, score)
                }

            bestResult = results
                .sortedByDescending { it.score }
                .map { it.toClassificationResult() }
                .first()
        }

        model?.close()
        return bestResult
    }
}