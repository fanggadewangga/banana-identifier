package com.fangga.features.home.presentation.state

import androidx.compose.runtime.Immutable
import com.fangga.features.home.data.static.Static
import com.fangga.features.home.domain.model.AboutApp
import com.fangga.features.home.domain.model.ListTipsAndRecommendation

@Immutable
data class HomeState(
    val tipsData: List<ListTipsAndRecommendation> = Static.tipsAndRecommendation,
    val aboutData: List<AboutApp> = Static.aboutApp, // TODO : Add Latest Result
    val isLoading: Boolean = false,
    val error: Throwable? = null
)