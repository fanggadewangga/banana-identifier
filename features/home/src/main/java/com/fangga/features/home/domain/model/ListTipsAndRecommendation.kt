package com.fangga.features.home.domain.model

import androidx.annotation.DrawableRes

data class ListTipsAndRecommendation(
    val id: String,
    val title: String,
    @DrawableRes val image: Int,
)
