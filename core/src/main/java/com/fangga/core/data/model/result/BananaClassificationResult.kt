package com.fangga.core.data.model.result

import com.fangga.core.data.model.enums.BananaType
import com.fangga.core.data.model.enums.RipenessType

data class BananaClassificationResult(
    val bananaType: BananaType,
    val ripenessType: RipenessType
)
