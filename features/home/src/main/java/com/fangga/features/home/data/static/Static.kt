package com.fangga.features.home.data.static

import com.fangga.core.resource.privacyIcon
import com.fangga.core.resource.ratingIcon
import com.fangga.core.resource.termsIcon
import com.fangga.core.resource.tipsBananaType
import com.fangga.core.resource.tipsRipenessType
import com.fangga.core.resource.tipsScan
import com.fangga.features.home.domain.model.AboutApp
import com.fangga.features.home.domain.model.ListTipsAndRecommendation

object Static {
    val tipsAndRecommendation = listOf(
        ListTipsAndRecommendation(
            id = "scan_tips",
            title = "Tips Mudah untuk Memindai Pisang dengan Akurat",
            image = tipsScan
        ),
        ListTipsAndRecommendation(
            id = "banana_type_tips",
            title = "Pisang yang Bisa Anda Scan di Aplikasi: Mengenal Jenis dan Karakteristiknya",
            image = tipsBananaType
        ),
        ListTipsAndRecommendation(
            id = "ripeness_tips",
            title = "Memahami Jenis & Tipe Kematangan Pisang",
            image = tipsRipenessType
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
}