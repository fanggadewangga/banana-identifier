package com.fangga.features.home.domain.model

import androidx.annotation.DrawableRes

data class AboutApp(
    val id: String,
    val title: String,
    @DrawableRes val icon: Int,
)
