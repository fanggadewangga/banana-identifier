package com.fangga.core.model.result

import androidx.annotation.DrawableRes
import com.fangga.core.model.enums.BananaType
import com.fangga.core.model.enums.RipenessType

data class Result(
    @DrawableRes val image: Int, // TODO: Change image type to other type
    val resultId: String,
    val bananaType: BananaType,
    val ripenessType: RipenessType,
    val timestamp: String,
)
