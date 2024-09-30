package com.fangga.features.home.data.static

import com.fangga.core.model.enums.BananaType
import com.fangga.core.model.enums.RipenessType
import com.fangga.core.model.result.ResultList
import com.fangga.core.resource.dummyResultImage
import com.fangga.core.resource.privacyIcon
import com.fangga.core.resource.ratingIcon
import com.fangga.core.resource.ripenessTips
import com.fangga.core.resource.scanTips
import com.fangga.core.resource.termsIcon
import com.fangga.features.home.domain.model.AboutApp
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

    val aboutApp = listOf(
        AboutApp(
            id = "privacy_policy",
            title = "Kebijakan Privasi",
            icon = privacyIcon
        ),
        AboutApp(
            id = "terms_and_conditions",
            title = "Syarat dan Ketentuan",
            icon = termsIcon
        ),
        AboutApp(
            id = "rating",
            title = "Rating App",
            icon = ratingIcon
        )
    )

    val dummyLatestResult = ResultList(
        image = dummyResultImage,
        resultId = "1",
        bananaType = BananaType.MOROSEBO,
        ripenessType = RipenessType.ALAMI,
        isActionRevealed = false,
        timestamp = "13 September 2024, 13:00"
    )
}