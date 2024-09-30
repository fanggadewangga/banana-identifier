package com.fangga.core.model.result

import androidx.annotation.DrawableRes
import com.fangga.core.model.enums.BananaType
import com.fangga.core.model.enums.RipenessType

data class ResultList(
    @DrawableRes val image: Int, // TODO: Change image type to ByteArray
    val resultId: String,
    val bananaType: BananaType,
    val ripenessType: RipenessType,
    var isActionRevealed: Boolean,
    val timestamp: String,
)
