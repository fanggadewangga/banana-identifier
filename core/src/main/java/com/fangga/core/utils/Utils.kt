package com.fangga.core.utils

import android.os.Build
import androidx.annotation.RequiresApi
import com.fangga.core.model.enums.BananaType
import com.fangga.core.model.enums.RipenessType
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun BananaType.toDescription(): String = this.description
fun RipenessType.toDescription(): String = this.description

@RequiresApi(Build.VERSION_CODES.O)
fun mapDateToFormattedString(date: LocalDateTime, format: String): String {
    val formatter = DateTimeFormatter.ofPattern(format)
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