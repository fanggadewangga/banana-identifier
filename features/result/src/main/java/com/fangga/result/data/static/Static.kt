package com.fangga.result.data.static

import com.fangga.core.model.enums.BananaType
import com.fangga.core.model.enums.RipenessType
import com.fangga.core.model.result.Result
import com.fangga.core.resource.resultImageDummy

object Static {
    val resultDetail = Result(
        image = resultImageDummy,
        resultId = "dummy-result",
        bananaType = BananaType.MOROSEBO,
        ripenessType = RipenessType.ALAMI,
        timestamp = "5 Oktober 2024"
    )
}