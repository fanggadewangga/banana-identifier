package com.fangga.core.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.util.Base64
import androidx.annotation.RequiresApi
import androidx.core.net.toUri
import com.fangga.core.data.model.enums.BananaType
import com.fangga.core.data.model.enums.RipenessType
import java.io.ByteArrayOutputStream
import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

fun BananaType.toDescription(): String = this.description
fun RipenessType.toDescription(): String = this.description

@RequiresApi(Build.VERSION_CODES.O)
fun mapDateToFormattedString(
    date: LocalDateTime,
    format: String = "dd MMMM yyyy · HH:mm",
    locale: Locale = Locale("id", "ID")
): String {
    val formatter = DateTimeFormatter.ofPattern(format, locale)
    return date.format(formatter)
}

fun singleClick(onClick: () -> Unit): () -> Unit {
    var latest: Long = 0
    return {
        val now = System.currentTimeMillis()
        if (now - latest >= 1000) {
            onClick()
            latest = now
        }
    }
}

fun getBitmapFromUri(context: Context, uri: Uri): Bitmap? {
    return try {
        val inputStream = context.contentResolver.openInputStream(uri)
        BitmapFactory.decodeStream(inputStream)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

fun converterBitmapToString(bitmap: Bitmap): String {
    val byteArrayOutputStream = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.JPEG, 70, byteArrayOutputStream)
    val byteArray = byteArrayOutputStream.toByteArray()
    return Base64.encodeToString(byteArray, Base64.DEFAULT)
}

fun converterStringToBitmap(encodedString: String): Bitmap? {
    return try {
        val encodeByte = Base64.decode(encodedString, Base64.DEFAULT)
        BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.size)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

fun saveBitmapToFileAndGetUri(context: Context, bitmap: Bitmap): String {
    val file = File(context.cacheDir, "captured_image.jpg")
    file.outputStream().use { out ->
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)
    }
    return file.toUri().toString()
}
