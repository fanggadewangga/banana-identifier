package com.fangga.savedresult.presentation.state

import com.fangga.core.data.model.result.ScanResultList

data class SavedResultState(
    val results: List<ScanResultList> = emptyList(),
    val isLoading: Boolean = false,
    val successMessage: String? = null,
    val errorMessage: String? = null,
    val showSuccessToast: Boolean = false,
    val showErrorToast: Boolean = false,
)
