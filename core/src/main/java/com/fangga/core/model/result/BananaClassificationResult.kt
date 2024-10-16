package com.fangga.core.model.result

import com.fangga.core.model.enums.BananaType
import com.fangga.core.model.enums.RipenessType

data class BananaClassificationResult(
    val bananaType: BananaType,
    val ripenessType: RipenessType
)
