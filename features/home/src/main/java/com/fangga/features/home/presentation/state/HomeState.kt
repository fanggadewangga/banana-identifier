package com.fangga.features.home.presentation.state

import androidx.compose.runtime.Immutable
import com.fangga.features.home.domain.model.ListTipsAndRecommendation

@Immutable
data class HomeState(
    val tipsData: List<ListTipsAndRecommendation> = emptyList(),
    val isLoading: Boolean = false,
    val error: Throwable? = null
)