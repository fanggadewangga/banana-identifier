package com.fangga.core.data.source.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ScanResult(
    @PrimaryKey
    var resultId: String,
    var bananaType: String,
    var ripenessType: String,
    var image: String,
    var timestamp: String
)
