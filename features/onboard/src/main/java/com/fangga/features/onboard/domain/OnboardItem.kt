package com.fangga.features.onboard.domain

import androidx.annotation.DrawableRes
import com.fangga.core.resource.firstOnboard
import com.fangga.core.resource.secondOnboard

sealed class OnboardItem(
    val title: String,
    val description: String,
    @DrawableRes val image: Int,
) {
    data object FirstOnboard : OnboardItem(
        image = firstOnboard,
        title = "Ketahui Kematangan Pisang dengan Sekali Klik!",
        description = "Scan pisang untuk mengetahui kematangannya, apakah belum matang, matang alami, atau matang kimia?",
    )

    data object SecondOnboard : OnboardItem(
        image = secondOnboard,
        title = "Temukan Jenis Pisang Anda dengan Satu Scan!",
        description = "Arahkan kamera ke pisang, dan Banana Identifier akan mengidentifikasi jenisnya dalam hitungan detik",
    )
}
