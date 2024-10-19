package com.fangga.savedresult.presentation.state

import com.fangga.core.data.model.result.ScanResultList

data class SavedResultState(
    val results: List<ScanResultList> = emptyList(),
    val isLoading: Boolean = false,
    val error: String = ""
)
