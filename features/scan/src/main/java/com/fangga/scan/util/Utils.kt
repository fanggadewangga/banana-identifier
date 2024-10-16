package com.fangga.scan.util

import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import androidx.core.content.ContextCompat
import com.fangga.core.model.enums.BananaType
import com.fangga.core.model.enums.RipenessType
import com.fangga.core.model.result.BananaClassificationResult
import com.fangga.scan.domain.BananaClassification

fun hasRequiredPermission(context: Context): Boolean {
    return Constants.CAMERAX_PERMISSION.all {
        ContextCompat.checkSelfPermission(
            context,
            it
        ) == PackageManager.PERMISSION_GRANTED
    }
}

fun rotateBitmap(bitmap: Bitmap, rotationDegrees: Int): Bitmap {
    val matrix = Matrix()
    matrix.postRotate(rotationDegrees.toFloat())
    return Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
}

fun BananaClassification.toClassificationResult(): BananaClassificationResult {
    val (bananaType, ripenessType) = when (this.name) {
        "Class 0" -> BananaType.AMBON to RipenessType.KIMIA
        "Class 1" -> BananaType.AMBON to RipenessType.ALAMI
        "Class 2" -> BananaType.HIJAU to RipenessType.KIMIA
        "Class 3" -> BananaType.HIJAU to RipenessType.ALAMI
        "Class 4" -> BananaType.KEPOK to RipenessType.KIMIA
        "Class 5" -> BananaType.KEPOK to RipenessType.ALAMI
        "Class 6" -> BananaType.MOROSEBO to RipenessType.KIMIA
        "Class 7" -> BananaType.MOROSEBO to RipenessType.ALAMI
        "Class 8" -> BananaType.RAJA to RipenessType.KIMIA
        "Class 9" -> BananaType.RAJA to RipenessType.ALAMI
        "Class 10" -> BananaType.SUSU to RipenessType.KIMIA
        else -> BananaType.SUSU to RipenessType.ALAMI
    }

    return BananaClassificationResult(bananaType, ripenessType)
}

fun getRotationDegreesFromUri(context: Context, uri: Uri): Int {
    val inputStream = context.contentResolver.openInputStream(uri)
    val exifInterface = ExifInterface(inputStream!!)
    val orientation = exifInterface.getAttributeInt(
        ExifInterface.TAG_ORIENTATION,
        ExifInterface.ORIENTATION_UNDEFINED
    )
    inputStream.close()
    return when (orientation) {
        ExifInterface.ORIENTATION_ROTATE_90 -> 90
        ExifInterface.ORIENTATION_ROTATE_180 -> 180
        ExifInterface.ORIENTATION_ROTATE_270 -> 270
        else -> 0
    }
}