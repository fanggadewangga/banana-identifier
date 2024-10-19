package com.fangga.core.utils

import android.net.Uri
import com.fangga.core.data.model.result.ScanResult
import com.fangga.core.data.model.result.ScanResultList
import com.fangga.core.data.source.room.entity.ScanResultEntity

fun ScanResultEntity.toScanResult(): ScanResult {
    return ScanResult(
        resultId = resultId, bananaType = bananaType,
        ripenessType = ripenessType,
        image = Uri.parse(image),
        timestamp = timestamp
    )
}

fun ScanResult.toScanResultEntity(): ScanResultEntity {
    return ScanResultEntity(
        resultId = resultId,
        bananaType = bananaType,
        ripenessType = ripenessType,
        image = image.toString(),
        timestamp = timestamp
    )
}

fun ScanResultEntity.toScanResultList(): ScanResultList {
    val imageFromString = converterStringToBitmap(image)
    return ScanResultList(
        resultId = resultId,
        bananaType = bananaType,
        ripenessType = ripenessType,
        image = imageFromString!!,
        timestamp = timestamp
    )
}

fun ScanResultList.toScanResultEntity(): ScanResultEntity {
    val imageToString = converterBitmapToString(image)
    return ScanResultEntity(
        resultId = resultId,
        bananaType = bananaType,
        ripenessType = ripenessType,
        image = imageToString,
        timestamp = timestamp
    )
}