package com.fangga.features.home.data.static

import com.fangga.core.resource.ripenessTips
import com.fangga.core.resource.scanTips
import com.fangga.features.home.domain.model.ListTipsAndRecommendation

object Static {
    val tipsAndRecommendation = listOf(
        ListTipsAndRecommendation(
            id = "scan_tips",
            title = "Tips Mudah untuk Memindai Pisang dengan Akurat",
            image = scanTips
        ),
        ListTipsAndRecommendation(
            id = "ripeness_tips",
            title = "Memahami Jenis & Tipe Kematangan Pisang",
            image = ripenessTips
        )
    )
}