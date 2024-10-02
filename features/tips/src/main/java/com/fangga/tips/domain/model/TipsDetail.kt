package com.fangga.tips.domain.model

import androidx.annotation.DrawableRes

data class TipsDetail(
    val tipsId: String,
    val title: String,
    val description: String,
    val steps: List<TipsStep>,
    @DrawableRes val imageSource: Int,
)
