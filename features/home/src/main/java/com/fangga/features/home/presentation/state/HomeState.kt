package com.fangga.features.home.presentation.state

import androidx.compose.runtime.Immutable
import com.fangga.core.data.model.result.ScanResultList
import com.fangga.features.home.data.static.Static
import com.fangga.features.home.domain.model.AboutApp
import com.fangga.features.home.domain.model.ListTipsAndRecommendation

@Immutable
data class HomeState(
    val tipsData: List<ListTipsAndRecommendation> = Static.tipsAndRecommendation,
    val aboutData: List<AboutApp> = Static.aboutApp,
    val latestResult: ScanResultList? = null,
    val isLoading: Boolean = false,
    val successMessage: String? = null,
    val errorMessage: String? = null,
    val showSuccessToast: Boolean = false,
    val showErrorToast: Boolean = false,
)